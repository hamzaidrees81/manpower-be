package com.manpower.service;

import com.manpower.model.InvoiceAsset;
import com.manpower.repository.InvoiceAssetRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceAssetService {
    private final InvoiceAssetRepository invoiceAssetRepository;

    public InvoiceAssetService(InvoiceAssetRepository invoiceAssetRepository) {
        this.invoiceAssetRepository = invoiceAssetRepository;
    }

    public List<InvoiceAsset> getAllInvoiceAssets() {
        return invoiceAssetRepository.findAll();
    }

    public Optional<InvoiceAsset> getInvoiceAssetById(Integer id) {
        return invoiceAssetRepository.findById(id);
    }

    public InvoiceAsset createInvoiceAsset(InvoiceAsset invoiceAsset) {
        return invoiceAssetRepository.save(invoiceAsset);
    }

    public void deleteInvoiceAsset(Integer id) {
        invoiceAssetRepository.deleteById(id);
    }

    public InvoiceAsset updateInvoiceAsset(Integer id, InvoiceAsset invoiceAsset) {
        throw new RuntimeException("Not implemented yet");

    }
}
