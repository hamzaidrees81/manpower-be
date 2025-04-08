package com.manpower.repository;

import com.manpower.model.AssetPayable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetPayableRepository extends JpaRepository<AssetPayable, Long> {
    List<AssetPayable> findByCompanyId(Integer companyId);
    List<AssetPayable> findByAssetIdAndCompanyId(Integer assetId, Integer companyId);
    List<AssetPayable> findByAssetIdAndCompanyIdAndPaymentStatus(Integer assetId, Integer companyId, String paymentStatus);
    List<AssetPayable> findByCompanyIdAndPaymentStatus(Integer companyId, String paymentStatus);

}
