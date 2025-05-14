package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.model.Asset;
import com.manpower.model.AssetProject;
import com.manpower.model.InvoiceAsset;
import com.manpower.model.InvoiceSponsorPayable;
import com.manpower.model.dto.AssetPayableDTO;
import com.manpower.model.dto.stats.AssetGeneralSummaryDTO;
import com.manpower.repository.AssetProjectRepository;
import com.manpower.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatsService {


    private final AssetService assetService;
    private final AssetProjectService assetProjectService;
    private final InvoiceService invoiceService;
    private final AssetProjectRepository assetProjectRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceAssetService invoiceAssetService;
    private final PaymentService paymentService;
    private final AssetPayableService assetPayableService;
    private final InvoiceSponsorPayableService invoiceSponsorPayableService;

    public StatsService(AssetService assetService, AssetProjectService assetProjectService, InvoiceService invoiceService, AssetProjectRepository assetProjectRepository, InvoiceRepository invoiceRepository, InvoiceAssetService invoiceAssetService, PaymentService paymentService, AssetPayableService assetPayableService, InvoiceSponsorPayableService invoiceSponsorPayableService) {
        this.assetService = assetService;
        this.assetProjectService = assetProjectService;
        this.invoiceService = invoiceService;
        this.assetProjectRepository = assetProjectRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceAssetService = invoiceAssetService;
        this.paymentService = paymentService;
        this.assetPayableService = assetPayableService;
        this.invoiceSponsorPayableService = invoiceSponsorPayableService;
    }

    public List<AssetGeneralSummaryDTO> getAssetsGeneralSummmary(){
        List<Asset> assets = assetService.getAllAssets();

        List<AssetGeneralSummaryDTO> assetGeneralSummaries = new ArrayList<>();

        for(Asset asset : assets) {
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

    public AssetGeneralSummaryDTO getAssetStats(Integer assetId) throws Exception {

    int activeProjects = 0;
    int totalPaidInvoices = 0;
    int totalUnpaidInvoices = 0;
    int totalUndueInvoices = 0;
    Float amountEarned;
    Float upcomingEarning;
    Float amountPaid;
    Float amountPayable;
    Float expenses;
    Float profitFromAsset;

//    //find this asset
//    Optional<Asset> assetOptional = assetService.getAssetById(assetId);
//    if(assetOptional.isEmpty()) {
//        throw new Exception("Asset not found");
//    }
//
//    //get all the projects of this asset
//    List<AssetProject> assetProject = assetProjectService.getActiveAssetProjectByAssetId(assetId);
//    activeProjects = assetProject.size();
//
//    //find the invoices raised for this asset
//    List<InvoiceAsset> assetInvoiceList = invoiceAssetService.findAssetInvoiceAssetId(assetId);
//
//    assetInvoiceList.getFirst().getInvoice().getStatus();

    return null;

    }
}
