package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.dto.InvoiceMetadata;
import com.manpower.mapper.CompanyMapper;
import com.manpower.mapper.InvoiceStatusMapper;
import com.manpower.model.*;
import com.manpower.model.dto.*;
import com.manpower.repository.*;
import com.manpower.specification.InvoiceSpecifications;
import com.manpower.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceAssetRepository invoiceAssetRepository;
    private final AssetRepository assetRepository;
    private final ProjectService projectService;
    private final AssetProjectService assetProjectService;
    private final TimesheetService timesheetService;
    private final ClientRepository clientRepository;
    private final CompanyRepository companyRepository;
    private final PreferenceService preferenceService;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Page<InvoiceStatusDTO> getFilteredInvoices(Integer clientId, Contants.PaymentStatus status, LocalDate invoiceStartDate, LocalDate invoiceEndDate, LocalDate createdStartDate, LocalDate createdEndDate, LocalDate clearedStartDate, LocalDate clearedEndDate, Pageable pageable) {
        Integer companyId = SecurityUtil.getCompanyClaim();
        Specification<Invoice> spec = InvoiceSpecifications.filterInvoices(companyId, clientId, status, invoiceStartDate, invoiceEndDate, createdStartDate, clearedEndDate, clearedStartDate, clearedEndDate);
        return invoiceRepository.findAll(spec, pageable).map(InvoiceStatusMapper::convertToDTO);
    }

    public Optional<DetailedInvoice> getDetailedInvoiceById(Integer id) {

        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if(invoiceOptional.isEmpty())
        {
            throw new RuntimeException("Invoice not found");
        }

        Invoice invoice = invoiceOptional.get();

        //get invoice assets
        Optional<List<InvoiceAsset>> invoiceAssetsOptional = invoiceAssetRepository.findByInvoice_Id(id);

        if(invoiceAssetsOptional.isEmpty() || invoiceAssetsOptional.get().isEmpty())
        {
            throw new RuntimeException("Invoice is empty");
        }

        Company companyDb = companyRepository.findById(invoice.getCompany().getId()).orElse(null);
        assert companyDb != null;
        CompanyDTO companyDTO = CompanyMapper.toDTO(companyDb);

        //prepare detailed object
        DetailedInvoice.DetailedInvoiceBuilder detailedInvoiceBuilder = DetailedInvoice.builder();
        detailedInvoiceBuilder.clientId(invoice.getClient().getClientId());
        detailedInvoiceBuilder.clientName(invoice.getClient().getName());
        detailedInvoiceBuilder.invoiceNumber(invoice.getNumber());
        detailedInvoiceBuilder.invoiceId(invoice.getId());
        detailedInvoiceBuilder.invoiceDate(invoice.getCreateDate());
        detailedInvoiceBuilder.startDate(invoice.getCreateDate());
        detailedInvoiceBuilder.endDate(invoice.getCreateDate());
        detailedInvoiceBuilder.clearedDate(invoice.getClearedDate());
        detailedInvoiceBuilder.company(companyDTO);
        detailedInvoiceBuilder.totalAmount(invoice.getTotalBeforeTax());
        detailedInvoiceBuilder.vatAmount(invoice.getTaxAmount());
        detailedInvoiceBuilder.totalWithVAT(invoice.getTotalAmountWithTax());
        detailedInvoiceBuilder.clientAddress(invoice.getClient().getAddress());


        //for this invoice, get list of all projects from asset project against assets
        List<InvoiceAsset> invoiceAssetList = invoiceAssetsOptional.get();

        // Get all projects from these asset projects
        HashMap<Integer, List<InvoiceAsset>> projectList = new HashMap<>();


        // Get unique projects
        for (InvoiceAsset invoiceAsset : invoiceAssetList) {
            int projectId = invoiceAsset.getAssetProject().getProject().getId();


            // Check if the project ID already exists in the map
            if (!projectList.containsKey(projectId)) {
                // If it doesn't exist, create a new list and add the asset project
                projectList.put(projectId, new ArrayList<>());
            }

            // Add the asset project to the existing list
            projectList.get(projectId).add(invoiceAsset);
        }

        List<DetailedProjectInvoice> listDetailedProjectInvoice = new ArrayList<>();
        detailedInvoiceBuilder.detailedProjectInvoiceList(listDetailedProjectInvoice);

        //now, for every project, we have related asset project list.
        //iterate on map and combine the resources
        for(Map.Entry<Integer, List<InvoiceAsset>> entry: projectList.entrySet())
        {
            //take a project and make an object
            DetailedProjectInvoice.DetailedProjectInvoiceBuilder detailedProjectInvoiceBuilder = DetailedProjectInvoice.builder();
            detailedProjectInvoiceBuilder.projectId(entry.getKey());
            detailedProjectInvoiceBuilder.projectName(entry.getValue().getFirst().getAssetProject().getProject().getName());
            detailedProjectInvoiceBuilder.projectNumber(entry.getValue().getFirst().getAssetProject().getProject().getProjectId());
            List<DetailedAssetInvoice> detailedAssetInvoiceList = new ArrayList<>();
            detailedProjectInvoiceBuilder.assetInvoicesList(detailedAssetInvoiceList);

            //make list of all resources within it
            for(InvoiceAsset invoiceAsset : entry.getValue())
            {
                DetailedAssetInvoice.DetailedAssetInvoiceBuilder detailedAssetInvoiceBuilder = DetailedAssetInvoice.builder();
                Asset asset = assetRepository.findById(invoiceAsset.getAsset().getId()).orElse(null);
                assert asset != null;
                BigDecimal regularTotal = invoiceAsset.getStandardHours().multiply(invoiceAsset.getStandardRate());
                BigDecimal otTotal = invoiceAsset.getOtHours().multiply(invoiceAsset.getOtRate());
                BigDecimal grandTotal = regularTotal.add(otTotal);
                BigDecimal vat = regularTotal.add(grandTotal.multiply(preferenceService.findVATAmount()));

                detailedAssetInvoiceBuilder.assetName(asset.getName());
                detailedAssetInvoiceBuilder.assetType(Contants.AssetType.fromValue(asset.getAssetType()));
                detailedAssetInvoiceBuilder.assetId(invoiceAsset.getAsset().getId());
                detailedAssetInvoiceBuilder.assetProjectId(invoiceAsset.getAssetProject().getProject().getId());
                detailedAssetInvoiceBuilder.regularHours(invoiceAsset.getStandardHours());
                detailedAssetInvoiceBuilder.overtimeHours(invoiceAsset.getOtHours());
                detailedAssetInvoiceBuilder.regularRate(invoiceAsset.getStandardRate());
                detailedAssetInvoiceBuilder.overtimeRate(invoiceAsset.getOtRate());
                detailedAssetInvoiceBuilder.regularTotal(regularTotal);
                detailedAssetInvoiceBuilder.otTotal(otTotal);
                detailedAssetInvoiceBuilder.totalAmount(grandTotal);
                detailedAssetInvoiceBuilder.vatAmount(vat);
                detailedAssetInvoiceBuilder.totalWithVAT(grandTotal.add(vat));

                detailedAssetInvoiceList.add(detailedAssetInvoiceBuilder.build());

            }
            listDetailedProjectInvoice.add(detailedProjectInvoiceBuilder.build());
        }


        return Optional.ofNullable(detailedInvoiceBuilder.build());
    }

    private BigDecimal calculateAssetPayable(BigDecimal rate, BigDecimal hours) {
        if (rate != null && hours != null && rate.compareTo(BigDecimal.ZERO) != 0 && hours.compareTo(BigDecimal.ZERO) != 0) {
            return rate.multiply(hours);
        }
        return BigDecimal.ZERO;
    }

    @Transactional
    public Invoice createInvoice(DetailedInvoice detailedInvoice) {

        Integer companyId = SecurityUtil.getCompanyClaim();

        String clientId = detailedInvoice.getClientId();

        //create invoiceBuilder
        Invoice.InvoiceBuilder invoiceBuilder = Invoice.builder();

        // Fetch existing client
        Client existingClient = clientRepository.findById(Integer.valueOf(clientId))
          .orElseThrow(() -> new RuntimeException("Client not found"));

        // Fetch existing company
        Company existingCompany = companyRepository.findById(companyId)
          .orElseThrow(() -> new RuntimeException("Company not found"));
        invoiceBuilder.company(existingCompany);

        BigDecimal tax = detailedInvoice.getTotalAmount().multiply(preferenceService.findVATAmount());
        invoiceBuilder.client(existingClient);
        invoiceBuilder.status(Contants.PaymentStatus.UNPAID.getValue());
        invoiceBuilder.startDate(detailedInvoice.getStartDate());
        invoiceBuilder.endDate(detailedInvoice.getEndDate());
        invoiceBuilder.createDate(detailedInvoice.getInvoiceDate());
        invoiceBuilder.totalBeforeTax(detailedInvoice.getTotalAmount());
        invoiceBuilder.taxAmount(tax);
        invoiceBuilder.totalAmountWithTax(detailedInvoice.getTotalAmount().add(tax));
        invoiceBuilder.number("INV-"+preferenceService.invoiceSequence());
        invoiceBuilder.creatorId((Integer) SecurityUtil.getClaim(Contants.RateType.Claims.USER_ID.name()));

        Invoice invoice = invoiceRepository.save(invoiceBuilder.build()); // Save invoice

        BigDecimal totalAssetPayable = BigDecimal.ZERO;
        BigDecimal totalSponsorPayable = BigDecimal.ZERO;

        //create invoice asset
        for(DetailedProjectInvoice detailedProjectInvoice: detailedInvoice.getDetailedProjectInvoiceList())
        {
            //for every project, make entry of asset
            for(DetailedAssetInvoice detailedAssetInvoice: detailedProjectInvoice.getAssetInvoicesList())
            {
                InvoiceAsset.InvoiceAssetBuilder invoiceAsset = InvoiceAsset.builder();

                AssetProject assetProject =assetProjectService.getAssetProjectById(detailedAssetInvoice.getAssetProjectId())
                  .orElseThrow(() -> new RuntimeException("Asset Project not found"));
                invoiceAsset.assetProject(assetProject);

                Integer assetId = detailedAssetInvoice.getAssetId();
                invoiceAsset.asset(Asset.builder().id(assetId).build());
                invoiceAsset.invoice(invoice);
                invoiceAsset.standardHours(detailedAssetInvoice.getRegularHours());
                invoiceAsset.otHours(detailedAssetInvoice.getOvertimeHours());
                invoiceAsset.standardRate(detailedAssetInvoice.getRegularRate());
                invoiceAsset.otRate(detailedAssetInvoice.getOvertimeRate());


                InvoiceAsset asset = invoiceAssetRepository.save(invoiceAsset.build());


                // calculate payable to asset and sponsor
                BigDecimal currentAssetPayable = BigDecimal.ZERO;

                // Calculate current asset payable (Standard + OT)
                currentAssetPayable = currentAssetPayable.add(calculateAssetPayable(asset.getStandardRate(), asset.getStandardHours()));
                currentAssetPayable = currentAssetPayable.add(calculateAssetPayable(asset.getOtRate(), asset.getOtHours()));

                // Calculate sponsor payable for this asset
                BigDecimal assetSponsorPayable = BigDecimal.ZERO;
                assetSponsorPayable = calculateSponsorPayable(asset, assetSponsorPayable, currentAssetPayable);

                // Add the calculated sponsor payable and asset payable to the totals
                totalSponsorPayable = totalSponsorPayable.add(assetSponsorPayable);
                totalAssetPayable = totalAssetPayable.add(currentAssetPayable);

            }
        }

        //store payables and profits in db
        invoice.setSponsorPayable(totalSponsorPayable);
        invoice.setAssetsPayable(totalAssetPayable);
        invoice.setProfit(totalAssetPayable.subtract(totalSponsorPayable).subtract(totalAssetPayable));
        invoiceRepository.save(invoice);

        preferenceService.updateInvoiceNumber();
        return invoice;
    }

    private static BigDecimal calculateSponsorPayable(InvoiceAsset asset, BigDecimal assetSponsorPayable, BigDecimal currentAssetPayable) {
        if (asset.getAsset().getSponsoredBy() != null) {
            String sponsorshipType = asset.getAsset().getSponsorshipType();
            BigDecimal sponsorshipValue = asset.getAsset().getSponsorshipValue();

            // Check if sponsorship type is fixed or percentage
            if (Contants.SponsorshipType.FIXED.name().equals(sponsorshipType)) {
                // Fixed sponsorship: add fixed amount
                assetSponsorPayable = assetSponsorPayable.add(sponsorshipValue != null ? sponsorshipValue : BigDecimal.ZERO);
            } else if (Contants.SponsorshipType.PERCENTAGE.name().equals(sponsorshipType)) {
                // Percentage sponsorship: calculate based on earned amount
                if (sponsorshipValue != null && sponsorshipValue.compareTo(BigDecimal.ZERO) != 0) {
                    assetSponsorPayable = assetSponsorPayable.add(sponsorshipValue.multiply(currentAssetPayable));
                }
            }
        }
        return assetSponsorPayable;
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice updateInvoice(Integer id, Invoice invoice) {
        throw new RuntimeException("Not implemented yet");
    }

    @Transactional
    public void addAssetToInvoice(Integer invoiceId, Integer assetId) {
        //check if the invoice and asset exists
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        Optional<Asset> asset = assetRepository.findById(assetId);

        if(invoice.isEmpty()) {
            throw new RuntimeException("Invoice not found");
        }
        if(asset.isEmpty()) {
            throw new RuntimeException("Asset not found");
        }

        //add asset to invoice
        InvoiceAsset invoiceAsset = new InvoiceAsset();
        invoiceAsset.setAsset(asset.get());
        invoiceAsset.setInvoice(invoice.get());
        invoiceAssetRepository.save(invoiceAsset);
    }

    /**
     * find all invoice assets and their parent invoice
     * @param invoiceId
     * @return
     */
    public Optional<List<InvoiceAsset>> getInvoiceWithAssetsById(Integer invoiceId)
    {
        return invoiceAssetRepository.findInvoiceAssetByInvoice_Id(invoiceId);
    }

    public DetailedInvoice createInvoiceTemplateFromClient(InvoiceMetadata invoiceMetadata) {

        //find all ongoing projects - where end date is greather than equal to our start date
        List<AssetProject> assetProjects = assetProjectService.findProjectByClientAndDate
          (invoiceMetadata.getClient(), invoiceMetadata.getEndDate());

        DetailedInvoice.DetailedInvoiceBuilder detailedInvoice = DetailedInvoice.builder();
        detailedInvoice.startDate(invoiceMetadata.getStartDate());
        detailedInvoice.endDate(invoiceMetadata.getEndDate());

        //get client name from ID
        Client client = clientRepository.findById(invoiceMetadata.getClient().getId()).get();
        detailedInvoice.clientName(client.getName());
         detailedInvoice.clientId(String.valueOf(client.getId()));

        Map<Integer,DetailedProjectInvoice> detailedProjectInvoiceList = new HashMap<>();

        //now check how much time each asset spend on the project during this time duration
        for(AssetProject assetProject : assetProjects) {

            //if the detailed project entry does not exist, create it
            if(!detailedProjectInvoiceList.containsKey(assetProject.getProject().getId())) {
                createProjectEntry(assetProject, detailedProjectInvoiceList);
            }

            DetailedAssetInvoice detailedAssetInvoice = getTimeSheetCalculations(invoiceMetadata, assetProject);

            //assign asset details to it
            detailedAssetInvoice.setAssetId(assetProject.getAsset().getId());
            detailedAssetInvoice.setAssetName(assetProject.getAsset().getName());
            detailedAssetInvoice.setAssetType(Contants.AssetType.fromValue(assetProject.getAsset().getAssetType()));
            detailedAssetInvoice.setAssetProjectId(assetProject.getId());

            //add this employee's calculation to list
            //extract the project lister from map
            DetailedProjectInvoice detailedProjectInvoice = detailedProjectInvoiceList.get(assetProject.getProject().getId());
            detailedProjectInvoice.getAssetInvoicesList().add(detailedAssetInvoice);

            detailedProjectInvoiceList.put(assetProject.getProject().getId(), detailedProjectInvoice);
        }

        //add all projects to final list
        detailedInvoice.detailedProjectInvoiceList(detailedProjectInvoiceList.values().stream().toList());
        return detailedInvoice.build();
    }

    private static void createProjectEntry(AssetProject assetProject, Map<Integer, DetailedProjectInvoice> detailedProjectInvoiceList) {
        DetailedProjectInvoice.DetailedProjectInvoiceBuilder detailedProjectInvoice = DetailedProjectInvoice.builder();
        detailedProjectInvoice.projectName(assetProject.getProject().getName());
        detailedProjectInvoice.projectId(assetProject.getProject().getId());
        detailedProjectInvoice.assetInvoicesList(new ArrayList<>());
        detailedProjectInvoiceList.put(assetProject.getProject().getId(), detailedProjectInvoice.build());
    }

    private DetailedAssetInvoice getTimeSheetCalculations(InvoiceMetadata invoiceMetadata, AssetProject assetProject) {
        //returns all timesheet entries for this resource on this project in a range
        List<Timesheet> timesheets =
          timesheetService.getTimesheetByAssetAndProjectAndDateRange(
            assetProject.getAsset().getId(), assetProject.getId(), invoiceMetadata.getStartDate(), invoiceMetadata.getEndDate());

        BigDecimal totalRegularHours = timesheets.stream()
          .filter(timesheet -> Contants.RateType.REGULAR.getValue() == timesheet.getRateType())
          .map(Timesheet::getHours)  // Extracts BigDecimal hours
          .reduce(BigDecimal.ZERO, BigDecimal::add);  // Sum all hours

        BigDecimal totalOvertimeHours = timesheets.stream()
          .filter(timesheet -> Contants.RateType.OVERTIME.getValue() == timesheet.getRateType())
          .map(Timesheet::getHours)  // Extracts BigDecimal hours
          .reduce(BigDecimal.ZERO, BigDecimal::add);  // Sum all hours

        BigDecimal regularRate = timesheets.stream()
          .filter(timesheet -> Contants.RateType.REGULAR.getValue() == timesheet.getRateType())
          .map(Timesheet::getRate)
          .findFirst()
          .orElse(BigDecimal.ZERO);

        BigDecimal overtimeRate = timesheets.stream()
          .filter(timesheet -> Contants.RateType.OVERTIME.getValue() == timesheet.getRateType())
          .map(Timesheet::getRate)
          .findFirst()
          .orElse(BigDecimal.ZERO);

        //put total values from timesheet to this attribute

        //create a total of this resource on this project
        DetailedAssetInvoice detailedAssetInvoice = DetailedAssetInvoice.builder().build();
        detailedAssetInvoice.setRegularRate(assetProject.getRegularRate());
        detailedAssetInvoice.setOvertimeRate(assetProject.getOvertimeRate());

        detailedAssetInvoice.setRegularRate(regularRate);
        detailedAssetInvoice.setOvertimeRate(overtimeRate);
        detailedAssetInvoice.setRegularHours(totalRegularHours);
        detailedAssetInvoice.setOvertimeHours(totalOvertimeHours);
        return detailedAssetInvoice;
    }


    public InvoiceStatusCompanyDTO getInvoicesForClientByStatus(Integer clientId, Contants.PaymentStatus status) {

        //get all invoices for this asset based on invoice status. If status is null, get all
        //find all invoice where this user is an asset
        Optional<List<Invoice>> invoicesListOptional = invoiceRepository.findInvoicesByClient_Id(clientId);

        if (invoicesListOptional.isEmpty()) {
            return InvoiceStatusCompanyDTO.builder().build();
        }

        List<InvoiceStatusDTO> assetInvoiceStatusDTOS = new ArrayList<>();

        //check all these invoice assets for parent invoice and mark as paid or not
        List<Invoice> invoiceList = invoicesListOptional.get();

        for (Invoice invoice : invoiceList) {

            //if status filter is enabled, skip this
            if(status != null) {
                if(Contants.PaymentStatus.fromValue(invoice.getStatus()) != status)
                    continue;
            }

            InvoiceStatusDTO.InvoiceStatusDTOBuilder builder = InvoiceStatusDTO.builder();
            builder.paymentStatus(Contants.PaymentStatus.fromValue(invoice.getStatus()))
              .invoiceNumber(invoice.getNumber())
              .creationDate(invoice.getCreateDate())
              .clearedDate(invoice.getClearedDate())
              .payableAmount(invoice.getTotalBeforeTax())
              .startDate(invoice.getStartDate())  // Added start date
              .endDate(invoice.getEndDate())      // Added end date
              .taxAmount(invoice.getTaxAmount())  // Added tax amount
              .totalAmountWithTax(invoice.getTotalAmountWithTax());  // Added total amount with tax

            assetInvoiceStatusDTOS.add(builder.build());
        }

        //calculate total amount

        BigDecimal totalPayableAmount = assetInvoiceStatusDTOS.stream()
          .map(InvoiceStatusDTO::getPayableAmount) // Extract payableAmount
          .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all amounts, starting from BigDecimal.ZERO


       return InvoiceStatusCompanyDTO.builder()
          .invoiceStatusDTOList(assetInvoiceStatusDTOS)
          .totalAmount(totalPayableAmount)
          .build();
    }


    public InvoiceStatusCompanyDTO getInvoicesForAllClientsByStatus(Contants.PaymentStatus status) {

        //get all invoices for this asset based on invoice status. If status is null, get all
        //find all invoice where this user is an asset
        Integer companyId = SecurityUtil.getCompanyClaim();
        Optional<List<Invoice>> invoicesListOptional = invoiceRepository.findInvoicesByCompany_Id(companyId);

        if (invoicesListOptional.isEmpty()) {
            return InvoiceStatusCompanyDTO.builder().build();
        }

        List<InvoiceStatusDTO> assetInvoiceStatusDTOS = new ArrayList<>();

        //check all these invoice assets for parent invoice and mark as paid or not
        List<Invoice> invoiceList = invoicesListOptional.get();

        for (Invoice invoice : invoiceList) {

            //if status filter is enabled, skip this
            if(status != null && status != Contants.PaymentStatus.ALL) {
                if(Contants.PaymentStatus.fromValue(invoice.getStatus()) != status)
                    continue;
            }

            InvoiceStatusDTO.InvoiceStatusDTOBuilder builder = InvoiceStatusDTO.builder();
            builder.paymentStatus(Contants.PaymentStatus.fromValue(invoice.getStatus()));
            builder.invoiceNumber(invoice.getNumber());
            builder.creationDate(invoice.getCreateDate());
            builder.clearedDate(invoice.getClearedDate());
            builder.payableAmount(invoice.getTotalBeforeTax());

            assetInvoiceStatusDTOS.add(builder.build());
        }

        //calculate total amount

        BigDecimal totalPayableAmount = assetInvoiceStatusDTOS.stream()
          .map(InvoiceStatusDTO::getPayableAmount) // Extract payableAmount
          .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all amounts, starting from BigDecimal.ZERO


        return InvoiceStatusCompanyDTO.builder()
          .invoiceStatusDTOList(assetInvoiceStatusDTOS)
          .totalAmount(totalPayableAmount)
          .build();
    }


    public List<InvoiceStatusDTO> getInvoicesForAssetByStatus(Integer assetId, Contants.PaymentStatus status) {

        //get all invoices for this asset based on invoice status. If status is null, get all
        //find all invoice where this user is an asset
        Optional<List<InvoiceAsset>> invoiceAssetListOptional = invoiceAssetRepository.findDistinctByAsset_Id(assetId);

        if (invoiceAssetListOptional.isEmpty()) {
            return Collections.emptyList();
        }
        List<InvoiceStatusDTO> assetInvoiceStatusDTOS = new ArrayList<>();

        //check all these invoice assets for parent invoice and mark as paid or not
        List<InvoiceAsset> invoiceAssets = invoiceAssetListOptional.get();

        for (InvoiceAsset invoiceAsset : invoiceAssets) {

            //if status filter is enabled, skip this
            if(status != null) {
                if(Contants.PaymentStatus.fromValue(invoiceAsset.getInvoice().getStatus()) != status)
                    continue;
            }

            InvoiceStatusDTO.InvoiceStatusDTOBuilder builder = InvoiceStatusDTO.builder();
            builder.paymentStatus(Contants.PaymentStatus.fromValue(invoiceAsset.getInvoice().getStatus()));
            builder.invoiceNumber(invoiceAsset.getInvoice().getNumber());
            builder.creationDate(invoiceAsset.getInvoice().getCreateDate());
            builder.clearedDate(invoiceAsset.getInvoice().getClearedDate());
//            builder.payableAmount();

            assetInvoiceStatusDTOS.add(builder.build());
        }

        return assetInvoiceStatusDTOS;
    }

    public List<InvoiceStatusDTO> getInvoicesForAllAssetsByStatus(Contants.PaymentStatus status) {
        //TODO: FIND ALL ASSETS IN OUR COMPANY
//
//        //get all invoices for this asset based on invoice status. If status is null, get all
//        //find all invoice where this user is an asset
//        Optional<List<InvoiceAsset>> invoiceAssetListOptional = invoiceAssetRepository.findDistinctByAsset_Id(assetId);
//
//        if (invoiceAssetListOptional.isEmpty()) {
//            return Collections.emptyList();
//        }
//        List<InvoiceStatusDTO> assetInvoiceStatusDTOS = new ArrayList<>();
//
//        //check all these invoice assets for parent invoice and mark as paid or not
//        List<InvoiceAsset> invoiceAssets = invoiceAssetListOptional.get();
//
//        for (InvoiceAsset invoiceAsset : invoiceAssets) {
//
//            //if status filter is enabled, skip this
//            if(status != null) {
//                if(Contants.InvoiceStatus.fromValue(invoiceAsset.getInvoice().getStatus()) != status)
//                    continue;
//            }
//
//            InvoiceStatusDTO.InvoiceStatusDTOBuilder builder = InvoiceStatusDTO.builder();
//            builder.invoiceStatus(Contants.InvoiceStatus.fromValue(invoiceAsset.getInvoice().getStatus()));
//            builder.invoiceNumber(invoiceAsset.getInvoice().getNumber());
//            builder.creationDate(invoiceAsset.getInvoice().getCreateDate());
//            builder.clearedDate(invoiceAsset.getInvoice().getClearedDate());
////            builder.payableAmount();
//
//            assetInvoiceStatusDTOS.add(builder.build());
//        }
//
//        return assetInvoiceStatusDTOS;
        return null;
    }

}
