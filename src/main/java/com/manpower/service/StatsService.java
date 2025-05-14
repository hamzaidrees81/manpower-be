package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.common.PaymentConstant;
import com.manpower.mapper.AssetMapper;
import com.manpower.model.*;
import com.manpower.model.dto.AssetDTO;
import com.manpower.model.dto.AssetPayableDTO;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.model.dto.PaymentFilterDTO;
import com.manpower.model.dto.stats.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final AssetService assetService;
    private final AssetProjectService assetProjectService;
    private final InvoiceAssetService invoiceAssetService;
    private final PaymentService paymentService;
    private final AssetPayableService assetPayableService;
    private final InvoiceSponsorPayableService invoiceSponsorPayableService;
    private final ClientService clientService;
    private final ProjectService projectService;
    private final InvoiceService invoiceService;

    /**
     * Get summary for clients
     */
    public ClientDetailedStatsDTO getClientsDetailedStats(Integer clientId) {

        Optional<Client> clientOtp = clientService.getClientById(clientId);
        if(clientOtp.isEmpty()) {
            return null;
        }

        Client client = clientOtp.get();

        List<Project> projects = projectService.findProjectByClient(client);
        int totalActiveProjects = projects.size();
        int totalProjects = projects.size();

        //total revenue - get list of all invoices for this client
        List<Invoice> invoices = invoiceService.getInvoicesForClient(client.getId());
        BigDecimal revenue = invoices.stream()
                .map(Invoice::getTotalAmountWithTax)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int invoicesCount = invoices.size();
        long paidInvoices = invoices.stream().filter(Objects::nonNull).filter(invoice -> invoice.getStatus().equals(Contants.PaymentStatus.PAID.getValue())).count();
        long unpaidInvoices = invoices.stream().filter(Objects::nonNull).filter(invoice -> invoice.getStatus().equals(Contants.PaymentStatus.UNPAID.getValue())).count();
        long pendingInvoices = invoices.stream().filter(Objects::nonNull).filter(invoice -> invoice.getStatus().equals(Contants.PaymentStatus.INVOICE_PENDING.getValue())).count();

        //total received against this invoice
        PaymentFilterDTO filterDTO = PaymentFilterDTO
                .builder()
                .paidToId(client.getId())
                .paidToType(PaymentConstant.PaidToType.INVOICE)
                .build();

        List<PaymentDTO> receivedInvoicePayments = paymentService.filterPayments(filterDTO);
        BigDecimal totalReceived = receivedInvoicePayments.stream()
                .map(PaymentDTO::getAmount)            // extract the BigDecimal field
                .filter(Objects::nonNull)              // optional: avoid nulls
                .reduce(BigDecimal.ZERO, BigDecimal::add); // sum them up

        BigDecimal outstandingAmount = revenue.subtract(totalReceived);

        //get payable to asset for this client
        List<AssetPayable> assetPayables =
                assetPayableService.findByClientId(client.getId());
        BigDecimal totalAssetPayable = assetPayables.stream()
                .map(dto -> Optional.ofNullable(dto.getPaidAmount()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<InvoiceSponsorPayable> sponsorPayables =
                invoiceSponsorPayableService.findPayablesByClientId(client.getId());
        BigDecimal totalSponsorPayables = sponsorPayables.stream()
                .map(dto -> Optional.ofNullable(dto.getPaidAmount()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal profit = revenue.subtract(totalAssetPayable).subtract(totalSponsorPayables);

        //total assets
        Long assetCount = assetProjectService.countAssetsOnProjectsByClientId(client.getId());

        BigDecimal profitabilityRatio = BigDecimal.ZERO;

        if (revenue.compareTo(BigDecimal.ZERO) > 0) {
            profitabilityRatio = profit
                    .divide(revenue, 4, RoundingMode.HALF_UP) // 4 decimal places for precision
                    .multiply(BigDecimal.valueOf(100));       // Convert to percentage
        }


        List<ProjectSummaryDTO> projectSummaryDTOS = new ArrayList<>();
        for(Project project : projects) {

            //find total assets on this project
            Long projectAssetCount = assetProjectService.countAssetsByProjectId(project.getId());

            ProjectSummaryDTO.builder()
                    .projectId(project.getId())
                    .projectName(project.getName())
                    .startDate(project.getStartDate())
                    .endDate(project.getEndDate())
                    .totalAssets(projectAssetCount.intValue())
                    .build();
        }

        ClientDetailedStatsDTO clientDetailedStatsDTO = ClientDetailedStatsDTO
                .builder()
                .clientId(client.getId())
                .clientName(client.getName())
                .totalProjects(totalProjects)
                .activeProjects(totalActiveProjects)
                .totalAssets(assetCount.intValue())

                .totalRevenue(revenue)
                .totalReceived(totalReceived)
                .profit(profit)
                .profitabilityRatio(profitabilityRatio)

                .invoiceCount(invoicesCount)
                .paidInvoices((int) paidInvoices)
                .unpaidInvoices((int) unpaidInvoices)
                .undueInvoices((int) pendingInvoices)

                .outstandingAmount(outstandingAmount)

                .projectSummaries(projectSummaryDTOS)
                .build();


        return clientDetailedStatsDTO;
    }

    /**
     * Get summary for clients
     */
    public List<ClientSummaryDTO> getClientsGeneralSummary() {

        List<ClientSummaryDTO> clientsGeneralSummary = new ArrayList<>();

        //get all clients
        List<Client> clients = clientService.getAllClientsRaw();

        //for every client, find a list of projects
        for (Client client : clients) {
            List<Project> projects = projectService.findProjectByClient(client);
            int totalActiveProjects = projects.size();
            int totalProjects = projects.size();

            //total revenue - get list of all invoices for this client
            List<Invoice> invoices = invoiceService.getInvoicesForClient(client.getId());
            BigDecimal revenue = invoices.stream()
                    .map(Invoice::getTotalAmountWithTax)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            //total received against this invoice
            PaymentFilterDTO filterDTO = PaymentFilterDTO
                    .builder()
                    .paidToId(client.getId())
                    .paidToType(PaymentConstant.PaidToType.INVOICE)
                    .build();

            List<PaymentDTO> receivedInvoicePayments = paymentService.filterPayments(filterDTO);
            BigDecimal totalReceived = receivedInvoicePayments.stream()
                    .map(PaymentDTO::getAmount)            // extract the BigDecimal field
                    .filter(Objects::nonNull)              // optional: avoid nulls
                    .reduce(BigDecimal.ZERO, BigDecimal::add); // sum them up

            BigDecimal outstandingAmount = revenue.subtract(totalReceived);

            //get payable to asset for this client
            List<AssetPayable> assetPayables =
                    assetPayableService.findByClientId(client.getId());
            BigDecimal totalAssetPayable = assetPayables.stream()
                    .map(dto -> Optional.ofNullable(dto.getPaidAmount()).orElse(BigDecimal.ZERO))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<InvoiceSponsorPayable> sponsorPayables =
                    invoiceSponsorPayableService.findPayablesByClientId(client.getId());
            BigDecimal totalSponsorPayables = sponsorPayables.stream()
                    .map(dto -> Optional.ofNullable(dto.getPaidAmount()).orElse(BigDecimal.ZERO))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal profit = revenue.subtract(totalAssetPayable).subtract(totalSponsorPayables);

            ClientSummaryDTO summaryDTO = ClientSummaryDTO
                    .builder()
                    .clientId(client.getId())
                    .clientName(client.getName())
                    .totalProjects(totalProjects)
                    .activeProjects(totalActiveProjects)
                    .totalRevenue(revenue)
                    .totalReceived(totalReceived)
                    .outstandingAmount(outstandingAmount)
                    .profit(profit)
                    .build();

            clientsGeneralSummary.add(summaryDTO);
        }
        return clientsGeneralSummary;
    }

    /**
     * return all asset stats summary on a specific project
     * @param projectId
     * @return
     */
    public List<AssetGeneralSummaryDTO> getAssetsOnProjectGeneralSummmary(Integer projectId){
        List<AssetDTO> assets = assetProjectService.getAssetsDTOByProjectId(projectId);
        return getAssetGeneralSummaryDTOS(assets);
    }


    public List<AssetGeneralSummaryDTO> getAssetsGeneralSummmary(){
        List<Asset> assets = assetService.getAllAssets();
        return getAssetGeneralSummaryDTOS(assets.stream().map(AssetMapper::toDTO).toList());
    }

    private List<AssetGeneralSummaryDTO> getAssetGeneralSummaryDTOS(List<AssetDTO> assets) {
        List<AssetGeneralSummaryDTO> assetGeneralSummaries = new ArrayList<>();

        for(AssetDTO asset : assets) {
            List<AssetProject> assetProjects
                    = assetProjectService.getActiveAssetProjectByAssetId(asset.getId());

            int totalActiveProjects = Math.toIntExact(assetProjects.stream()
                    .filter(assetProject -> assetProject.getIsActive() == 1)
                    .count());

            Contants.EngagementStatus engagementStatus = totalActiveProjects > 0 ? Contants.EngagementStatus.ENGAGED : Contants.EngagementStatus.FREE;

            //amount earned
            //get all invoices and calculate total
            List<InvoiceAsset> assetInvoices = invoiceAssetService.findAssetInvoiceAssetId(asset.getId());
            //get total revenue of these invoices
            BigDecimal revenue = assetInvoices.stream()
                    .map(invoiceAsset ->
                            invoiceAsset.getOtRate().multiply(invoiceAsset.getOtHours())
                                    .add(invoiceAsset.getStandardRate().multiply(invoiceAsset.getStandardHours()))
                    )
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal assetExpense = paymentService.getExpensesByAsset(asset.getId());

            /************************/
            //to get profit from asset, take earning, then subtract sponsor payable, asset payable, then expenses
            List<AssetPayableDTO> assetPayables = assetPayableService.findPayablesByAssetId(asset.getId());

            BigDecimal totalAssetPayable = assetPayables.stream()
                    .map(dto -> Optional.ofNullable(dto.getPaidAmount()).orElse(BigDecimal.ZERO))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<InvoiceSponsorPayable> sponsorPayables = invoiceSponsorPayableService.findPayablesByAssetId(asset.getId());
            BigDecimal totalSponsorPayable = sponsorPayables.stream()
                    .map(sp -> Optional.ofNullable(sp.getPaidAmount()).orElse(BigDecimal.ZERO))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal profit = revenue.subtract(totalAssetPayable).subtract(totalSponsorPayable).subtract(assetExpense);


            AssetGeneralSummaryDTO summary =
                    AssetGeneralSummaryDTO.builder()
                            .assetId(asset.getId())
                            .assetName(asset.getName())
                            .status(engagementStatus)
                            .activeProjects(totalActiveProjects)
                            .revenueEarned(revenue)
                            .expenses(assetExpense)
                            .profitFromAsset(profit)
                            .build();
            assetGeneralSummaries.add(summary);
        }
        return assetGeneralSummaries;
    }

    public AssetDetailedStatsDTO getAssetStats(Integer assetId) throws Exception {

        Optional<Asset> assetOpt = assetService.getAssetById(assetId);
        if(assetOpt.isEmpty()) {
            throw new Exception("Asset not found");
        }
        Asset asset = assetOpt.get();

        List<AssetProject> assetProjects
                = assetProjectService.getActiveAssetProjectByAssetId(assetId);

        int totalActiveProjects = Math.toIntExact(assetProjects.stream()
                .filter(assetProject -> assetProject.getIsActive() == 1)
                .count());

        Contants.EngagementStatus engagementStatus = totalActiveProjects > 0 ? Contants.EngagementStatus.ENGAGED : Contants.EngagementStatus.FREE;

        //amount earned
        //get all invoices and calculate total
        List<InvoiceAsset> assetInvoices = invoiceAssetService.findAssetInvoiceAssetId(assetId);
        //get total revenue of these invoices
        BigDecimal revenue = assetInvoices.stream()
                .map(invoiceAsset ->
                        invoiceAsset.getOtRate().multiply(invoiceAsset.getOtHours())
                                .add(invoiceAsset.getStandardRate().multiply(invoiceAsset.getStandardHours()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //total paid invoice count
        int paidInvoicesCount = (int) assetInvoices.stream().filter(invoiceAsset ->
                invoiceAsset.getInvoice().getStatus().equals(Contants.PaymentStatus.PAID.getValue()))
                .count();

        int unpaidInvoicesCount = (int) assetInvoices.stream().filter(invoiceAsset ->
                        invoiceAsset.getInvoice().getStatus().equals(Contants.PaymentStatus.UNPAID.getValue()))
                .count();

        int undueInvoicesCount = (int) assetInvoices.stream().filter(invoiceAsset ->
                        invoiceAsset.getInvoice().getStatus().equals(Contants.PaymentStatus.INVOICE_PENDING.getValue()))
                .count();

        BigDecimal assetExpense = paymentService.getExpensesByAsset(assetId);
        List<PaymentDTO> assetPayments  = paymentService.getPaymentsToAsset(assetId);

        /************************/
        //to get profit from asset, take earning, then subtract sponsor payable, asset payable, then expenses
        List<AssetPayableDTO> assetPayables = assetPayableService.findPayablesByAssetId(assetId);

        BigDecimal totalAssetPayable = assetPayables.stream()
                .map(dto -> Optional.ofNullable(dto.getPaidAmount()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<InvoiceSponsorPayable> sponsorPayables = invoiceSponsorPayableService.findPayablesByAssetId(assetId);
        BigDecimal totalSponsorPayable = sponsorPayables.stream()
                .map(sp -> Optional.ofNullable(sp.getPaidAmount()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal profit = revenue.subtract(totalAssetPayable).subtract(totalSponsorPayable).subtract(assetExpense);

        BigDecimal amountPaidToAsset = paymentService.getAmountPaidToAsset(assetId);

        BigDecimal profitabilityRatio = BigDecimal.ZERO;

        if (revenue.compareTo(BigDecimal.ZERO) > 0) {
            profitabilityRatio = profit
                    .divide(revenue, 4, RoundingMode.HALF_UP) // 4 decimal places for precision
                    .multiply(BigDecimal.valueOf(100));       // Convert to percentage
        }


        //projects
        List<ProjectSummaryDTO> projectSummaryDTOS = new ArrayList<>();
        for(AssetProject project : assetProjects) {
            ProjectSummaryDTO projectSummaryDTO = ProjectSummaryDTO.builder()
                    .projectId(project.getProject().getId())
                    .projectName(project.getProject().getName())
                    .startDate(project.getProject().getStartDate())
                    .endDate(project.getProject().getEndDate())
                    .status(Contants.AssetProjectStatus.fromValue(project.getStatus()).name())
                    .build();
            projectSummaryDTOS.add(projectSummaryDTO);
        }

        //invoices

        List<InvoiceSummaryDTO> invoiceSummaryDTOS = new ArrayList<>();
        for(InvoiceAsset invoiceAsset : assetInvoices) {
            Invoice invoice = invoiceAsset.getInvoice();
            InvoiceSummaryDTO invoiceSummaryDTO = InvoiceSummaryDTO
                    .builder()
                    .invoiceId(invoice.getId())
                    .invoiceNumber(invoice.getNumber())
                    .invoiceDate(invoice.getCreateDate())
                    .amount(invoice.getTotalAmountWithTax().floatValue())
                    .status(Contants.PaymentStatus.fromValue(invoice.getStatus()).name())
                    .build();
            invoiceSummaryDTOS.add(invoiceSummaryDTO);
        }

        List<PaymentSummaryDTO> paymentSummaryDTOS = new ArrayList<>();
        for(PaymentDTO payment : assetPayments)
        {
            PaymentSummaryDTO paymentDTO = PaymentSummaryDTO
                    .builder()
                    .paymentId(payment.getId())
                    .paymentDate(payment.getPaymentDate())
                    .amount(payment.getAmount().floatValue())
                    .status(payment.getStatus().name())
                    .build();
        }

        AssetDetailedStatsDTO.AssetDetailedStatsDTOBuilder stats = AssetDetailedStatsDTO.builder();
        stats.assetId(assetId);
        stats.assetName(asset.getName());
        stats.assetType(Contants.AssetType.fromValue(asset.getAssetType()).name());
        stats.status(engagementStatus.name());

        stats.activeProjects(totalActiveProjects);
        stats.totalInvoiceCount(assetInvoices.size());
        stats.paidInvoiceCount(paidInvoicesCount);
        stats.unpaidInvoiceCount(unpaidInvoicesCount);
        stats.undueInvoiceCount(undueInvoicesCount);

        stats.totalRevenue(revenue.floatValue());
        stats.amountEarned(totalAssetPayable.floatValue());
        stats.amountPaid(amountPaidToAsset.floatValue());

        stats.totalExpenses(assetExpense.floatValue());
        stats.profitFromAsset(profit.floatValue());
        stats.sponsorPayable(totalSponsorPayable.floatValue());
        stats.profitabilityRatio(profitabilityRatio.floatValue());


        stats.projectAssignments(projectSummaryDTOS);
        stats.invoices(invoiceSummaryDTOS);
        stats.payments(paymentSummaryDTOS);
        return stats.build();

    }
}
