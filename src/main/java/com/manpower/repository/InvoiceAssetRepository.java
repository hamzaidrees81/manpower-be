package com.manpower.repository;

import com.manpower.model.Invoice;
import com.manpower.model.InvoiceAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceAssetRepository extends JpaRepository<InvoiceAsset, Integer> {
  Optional<List<InvoiceAsset>> findByInvoice_Id(Integer invoiceId);
  Optional<List<InvoiceAsset>> findInvoiceAssetByInvoice_Id(Integer invoiceId);
  Optional<List<InvoiceAsset>> findInvoiceAssetByAsset_Id(Integer assetId);
//  Optional<List<Invoice>> invoiceAssetRepository.findDistinctIByAsset_Id(assetId);
  Optional<List<InvoiceAsset>> findDistinctByAsset_Id(Integer assetId);
  List<InvoiceAsset> findInvoiceAssetByAssetProject_Project(Integer projectId);
}
