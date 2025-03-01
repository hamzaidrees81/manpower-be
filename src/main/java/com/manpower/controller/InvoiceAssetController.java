package com.manpower.controller;

import com.manpower.model.InvoiceAsset;
import com.manpower.service.InvoiceAssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-assets")
public class InvoiceAssetController {
    private final InvoiceAssetService invoiceAssetService;

    public InvoiceAssetController(InvoiceAssetService invoiceAssetService) {
        this.invoiceAssetService = invoiceAssetService;
    }

    @GetMapping
    public List<InvoiceAsset> getAllInvoiceAssets() {
        return invoiceAssetService.getAllInvoiceAssets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceAsset> getInvoiceAssetById(@PathVariable Integer id) {
        return invoiceAssetService.getInvoiceAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InvoiceAsset createInvoiceAsset(@RequestBody InvoiceAsset invoiceAsset) {
        return invoiceAssetService.createInvoiceAsset(invoiceAsset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceAsset> updateInvoiceAsset(@PathVariable Integer id, @RequestBody InvoiceAsset invoiceAsset) {
        return ResponseEntity.ok(invoiceAssetService.updateInvoiceAsset(id, invoiceAsset));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceAsset(@PathVariable Integer id) {
        invoiceAssetService.deleteInvoiceAsset(id);
        return ResponseEntity.noContent().build();
    }
}
