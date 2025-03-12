package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.dto.InvoiceMetadata;
import com.manpower.model.*;
import com.manpower.model.dto.DetailedAssetInvoice;
import com.manpower.model.dto.DetailedInvoice;
import com.manpower.model.dto.DetailedProjectInvoice;
import com.manpower.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Transactional
    public Invoice createInvoice(DetailedInvoice detailedInvoice) {

        //TODO: HARD CODED COMPANY
        Integer companyId = 1;

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

        invoiceBuilder.client(existingClient);
        invoiceBuilder.status(Contants.InvoiceStatus.UNPAID.getValue());
        invoiceBuilder.startDate(detailedInvoice.getStartDate());
        invoiceBuilder.endDate(detailedInvoice.getEndDate());
        invoiceBuilder.createDate(detailedInvoice.getInvoiceDate());
        invoiceBuilder.totalAmount(detailedInvoice.getTotalAmount());

        Invoice invoice = invoiceBuilder.build();
        invoiceRepository.save(invoice); // Save invoice

        //create invoice asset
        for(DetailedProjectInvoice detailedProjectInvoice: detailedInvoice.getDetailedProjectInvoiceList())
        {
            //for every project, make entry of asset
            Integer projectId = detailedProjectInvoice.getProjectId();

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

                invoiceAssetRepository.save(invoiceAsset.build());
            }
        }
        return invoice;
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice updateInvoice(Integer id, Invoice invoice) {
        throw new RuntimeException("Not implemented yet");
    }

    public Optional<Invoice> getInvoiceByClientId(String clientId) {
        return invoiceRepository.findByClient_ClientId(clientId);
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
    public Optional<InvoiceAsset> getInvoiceWithAssetsById(Integer invoiceId)
    {
        return invoiceAssetRepository.findByInvoice_Id(invoiceId);
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

}
