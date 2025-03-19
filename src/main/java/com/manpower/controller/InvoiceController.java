package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.dto.InvoiceMetadata;
import com.manpower.model.Invoice;
import com.manpower.model.dto.InvoiceStatusCompanyDTO;
import com.manpower.model.dto.InvoiceStatusDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<DetailedInvoice> getDetailedInvoiceById(@PathVariable Integer id) {
        return invoiceService.getDetailedInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/invoices/{assetId}/{status}", name = "Get all invoices for this asset based on status as paid/unpaid.  If status is null, get all.")
    public ResponseEntity<List<InvoiceStatusDTO>> getInvoiceForAssetByStatus(@PathVariable Integer assetId, @PathVariable Contants.InvoiceStatus status) {
        return ResponseEntity.ok().body(invoiceService.getInvoicesForAssetByStatus(assetId, status));
    }

    @GetMapping(value = "/invoices/client/{clientId}/{status}", name = "Get all invoices for this client based on status as paid/unpaid.  If status is null, get all.")
    public ResponseEntity<InvoiceStatusCompanyDTO> getInvoiceForClientByStatus(@PathVariable Integer clientId, @PathVariable Contants.InvoiceStatus status) {
        return ResponseEntity.ok().body(invoiceService.getInvoicesForClientByStatus(clientId, status));
    }



//    @PostMapping("/addAsset/{invoiceId}/{assetId}")
//    public ResponseEntity<Invoice> addAssetToInvoice(@PathVariable Integer invoiceId, @PathVariable Integer assetId) {
//        invoiceService.addAssetToInvoice(invoiceId, assetId);
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/{invoiceId}/assets")
//    public ResponseEntity<List<InvoiceAsset>> getInvoiceAssets(@PathVariable Integer invoiceId) {
//        return invoiceService.getInvoiceWithAssetsById(invoiceId)
//          .map(ResponseEntity::ok)
//          .orElse(ResponseEntity.notFound().build());
//    }

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
