package com.manpower.repository;

import com.manpower.model.AssetPayable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetPayableRepository extends JpaRepository<AssetPayable, Long> {
    List<AssetPayable> findByAssetIdAndCompany(Integer assetId);
}
