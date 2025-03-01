package com.manpower.repository;

import com.manpower.model.InvoiceAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceAssetRepository extends JpaRepository<InvoiceAsset, Integer> {
}
