package com.manpower.repository;

import com.manpower.model.InvoiceAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceAssetRepository extends JpaRepository<InvoiceAsset, Integer> {
  Optional<InvoiceAsset> findByInvoice_Id(Integer invoiceId);
}
