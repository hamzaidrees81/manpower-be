package com.manpower.repository;

import com.manpower.model.AssetPayable;
import com.manpower.model.InvoiceSponsorPayable;
import com.manpower.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceSponsorPayableRepository extends JpaRepository<InvoiceSponsorPayable, Long> {
    List<InvoiceSponsorPayable> findByCompanyId(Integer companyId);
    List<InvoiceSponsorPayable> findBySponsorIdAndCompanyId(Integer assetId, Integer companyId);
    List<InvoiceSponsorPayable> findBySponsorIdAndCompanyIdAndPaymentStatus(Integer assetId, Integer companyId, String paymentStatus);
    List<InvoiceSponsorPayable> findByCompanyIdAndPaymentStatus(Integer companyId, String paymentStatus);
    List<InvoiceSponsorPayable> findBySponsorshipAsset_Id(Integer assetId);
    List<InvoiceSponsorPayable> findByInvoice_Id(Integer invoiceId);
    List<InvoiceSponsorPayable> findByInvoice_Client_Id(Integer clientId);
    public List<InvoiceSponsorPayable> findByProjectSponsorshipId_AssetProject_Project(Project project);



}
