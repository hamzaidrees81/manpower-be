package com.manpower.repository;

import com.manpower.model.AssetPayable;
import com.manpower.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetPayableRepository extends JpaRepository<AssetPayable, Long> {
    List<AssetPayable> findByCompanyId(Integer companyId);
    List<AssetPayable> findByAssetIdAndCompanyId(Integer assetId, Integer companyId);
    List<AssetPayable> findByAssetIdAndCompanyIdAndPaymentStatus(Integer assetId, Integer companyId, String paymentStatus);
    List<AssetPayable> findByCompanyIdAndPaymentStatus(Integer companyId, String paymentStatus);
    List<AssetPayable> findByInvoice_Id(Integer invoiceId);
    List<AssetPayable> findByInvoice_Client_Id(Integer clientId);
    List<AssetPayable> findByAssetProject_Project(Project project);

}
