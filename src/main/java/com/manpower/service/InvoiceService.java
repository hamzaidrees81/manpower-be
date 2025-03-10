package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.dto.InvoiceMetadata;
import com.manpower.model.*;
import com.manpower.repository.AssetRepository;
import com.manpower.repository.InvoiceAssetRepository;
import com.manpower.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceAssetRepository invoiceAssetRepository;
    private final AssetRepository assetRepository;
    private final ProjectService projectService;
    private final AssetProjectService assetProjectService;
    private final TimesheetService timesheetService;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceAssetRepository invoiceAssetRepository, AssetRepository assetRepository, ProjectService projectService, AssetProjectService assetProjectService, TimesheetService timesheetService) {
        this.invoiceRepository = invoiceRepository;
      this.invoiceAssetRepository = invoiceAssetRepository;
        this.assetRepository = assetRepository;
        this.projectService = projectService;
        this.assetProjectService = assetProjectService;
        this.timesheetService = timesheetService;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
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

    @Transactional
    public Invoice createInvoiceWithAssets(InvoiceWithAsset invoiceAndAssets) {

        int companyId = 1; //TODO: replace it from token

        //create invoice
        Invoice invoice = invoiceAndAssets.getInvoice();
        invoice.setCompany(Company.builder().id(companyId).build());
        invoice.setStatus(Contants.InvoiceStatus.UNPAID.getValue());
        invoice =  createInvoice(invoiceAndAssets.getInvoice());

        //using this Invoice ID, create invoice items

        for(InvoiceAsset asset : invoiceAndAssets.getInvoiceAssetList()) {
            //Step1: get the projects of this Asset for this company which this employee worked on between selected dates
            List<AssetProject> assetProjects = assetProjectService.getProjectsByAssetBetweenDate(
             asset.getId(), invoice.getStartDate(), invoice.getEndDate());

            /**
             * 2. Get the projects this resource is working on
             * 3. Get the hours this resource spent on this project
             * 4. Get the cost as well
             * 5. Assign all this to InvoiceAsset
             */
            for(AssetProject assetProject : assetProjects) {
                Optional<List<Timesheet>> timeSheetsForThisProject =
                  timesheetService.getTimesheetByAssetOnProjectBetweenDate(asset.getAsset().getId(),
                    assetProject.getProject().getId(),  invoice.getStartDate(), invoice.getEndDate());

                BigDecimal regularHours = BigDecimal.ZERO;
                BigDecimal overtimeHours = BigDecimal.ZERO;

                if(timeSheetsForThisProject.isPresent()) {
                    for(Timesheet timesheet : timeSheetsForThisProject.get()) {
                        if(timesheet.getRateType() == Contants.RateType.REGULAR.getValue())
                            regularHours = regularHours.add(timesheet.getHours());
                        if(timesheet.getRateType() == Contants.RateType.OVERTIME.getValue())
                            overtimeHours = overtimeHours.add(timesheet.getHours());
                    }
                }

                //now we have hours and timesheet, let's calculate total spend on this project
                InvoiceAsset invoiceAsset = new InvoiceAsset();
                invoiceAsset.setInvoice(invoice);
                invoiceAsset.setOtHours(overtimeHours);
                invoiceAsset.setAsset(asset.getAsset());
                invoiceAsset.setStandardHours(regularHours);
                invoiceAsset.setAssetProject(assetProject);
                invoiceAsset.setOtRate(assetProject.getOvertimeRate());
                invoiceAsset.setStandardRate(assetProject.getRegularRate());
                invoiceAssetRepository.save(invoiceAsset);

            }



        }
        return null;
    }

    public void createInvoiceTemplateFromClient(InvoiceMetadata invoiceMetadata) {

        //find all projects of this client
//        List<Project> clientProjects = projectService.findProjectByClientAndDate
//          (invoiceMetadata.getClient(), invoiceMetadata.getStartDate(), invoiceMetadata.getEndDate());

        List<Project> clientProjects = projectService.findProjectByClient(invoiceMetadata.getClient());

        int a=0;

    }

}
