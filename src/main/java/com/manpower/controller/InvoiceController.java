package com.manpower.controller;

import com.manpower.dto.InvoiceMetadata;
import com.manpower.model.Invoice;
import com.manpower.model.InvoiceAsset;
import com.manpower.model.InvoiceWithAsset;
import com.manpower.model.dto.DetailedInvoice;
import com.manpower.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Integer id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addAsset/{invoiceId}/{assetId}")
    public ResponseEntity<Invoice> addAssetToInvoice(@PathVariable Integer invoiceId, @PathVariable Integer assetId) {
        invoiceService.addAssetToInvoice(invoiceId, assetId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{invoiceId}/assets")
    public ResponseEntity<InvoiceAsset> addAssetToInvoice(@PathVariable Integer invoiceId) {
        return invoiceService.getInvoiceWithAssetsById(invoiceId)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/prepare-invoice")
    public DetailedInvoice prepareInvoice(@RequestBody InvoiceMetadata invoiceMetadata) {
        return invoiceService.createInvoiceTemplateFromClient(invoiceMetadata);
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody DetailedInvoice detailedInvoice) {
        return invoiceService.createInvoice(detailedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Integer id, @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Integer id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
