package com.manpower.repository;

import com.manpower.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
