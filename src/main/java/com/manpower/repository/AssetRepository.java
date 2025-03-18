package com.manpower.repository;

import com.manpower.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
        List<Asset> findByCompany_Id(Integer companyId);
}
