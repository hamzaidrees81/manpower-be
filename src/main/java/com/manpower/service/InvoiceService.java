package com.manpower.service;

import com.manpower.model.Asset;
import com.manpower.model.Invoice;
import com.manpower.model.InvoiceAsset;
import com.manpower.repository.AssetRepository;
import com.manpower.repository.InvoiceAssetRepository;
import com.manpower.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceAssetRepository invoiceAssetRepository;
    private final AssetRepository assetRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceAssetRepository invoiceAssetRepository, AssetRepository assetRepository) {
        this.invoiceRepository = invoiceRepository;
      this.invoiceAssetRepository = invoiceAssetRepository;
        this.assetRepository = assetRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Integer id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice updateInvoice(Integer id, Invoice invoice) {
        throw new RuntimeException("Not implemented yet");
    }

    public Optional<Invoice> getInvoiceByClientId(String clientId) {
        return invoiceRepository.findByClient_ClientId(clientId);
    }

    @Transactional
    public void addAssetToInvoice(Integer invoiceId, Integer assetId) {
        //check if the invoice and asset exists
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        Optional<Asset> asset = assetRepository.findById(assetId);

        if(invoice.isEmpty()) {
            throw new RuntimeException("Invoice not found");
        }
        if(asset.isEmpty()) {
            throw new RuntimeException("Asset not found");
        }

        //add asset to invoice
        InvoiceAsset invoiceAsset = new InvoiceAsset();
        invoiceAsset.setAsset(asset.get());
        invoiceAsset.setInvoice(invoice.get());
        invoiceAssetRepository.save(invoiceAsset);
    }

    /**
     * find all invoice assets and their parent invoice
     * @param invoiceId
     * @return
     */
    public Optional<InvoiceAsset> getInvoiceWithAssetsById(Integer invoiceId)
    {
        return invoiceAssetRepository.findByInvoice_Id(invoiceId);
    }
}
