package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.dto.InvoiceMetadata;
import com.manpower.model.Invoice;
import com.manpower.model.dto.InvoiceStatusCompanyDTO;
import com.manpower.model.dto.InvoiceStatusDTO;
import com.manpower.model.dto.DetailedInvoice;
import com.manpower.model.dto.ListInvoicesResponse;
import com.manpower.service.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedInvoice> getDetailedInvoiceById(@PathVariable Integer id) throws Exception {
        return invoiceService.getDetailedInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ListInvoicesResponse listInvoices(
      @RequestParam(required = false) Integer clientId,
      @RequestParam(required = false) Contants.PaymentStatus status,
      @RequestParam(required = false) LocalDate invoiceStartDate,
      @RequestParam(required = false) LocalDate invoiceEndDate,
      @RequestParam(required = false) LocalDate createdStartDate,
      @RequestParam(required = false) LocalDate createdEndDate,
      @RequestParam(required = false) LocalDate clearedStartDate,
      @RequestParam(required = false) LocalDate clearedEndDate,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return invoiceService.getFilteredInvoices(clientId, status, invoiceStartDate, invoiceEndDate, createdStartDate, createdEndDate, clearedStartDate, clearedEndDate, pageable);
    }

    @GetMapping(value = "/invoices/{assetId}/{status}", name = "Get all invoices for this asset based on status as paid/unpaid.  If status is null, get all.")
    public ResponseEntity<List<InvoiceStatusDTO>> getInvoiceForAssetByStatus(@PathVariable Integer assetId, @PathVariable Contants.PaymentStatus status) {
        return ResponseEntity.ok().body(invoiceService.getInvoicesForAssetByStatus(assetId, status));
    }

    @GetMapping(value = "/invoices/client/{clientId}/{status}", name = "Get all invoices for this client based on status as paid/unpaid.  If status is null, get all.")
    public ResponseEntity<InvoiceStatusCompanyDTO> getInvoiceForClientByStatus(@PathVariable Integer clientId, @PathVariable Contants.PaymentStatus status) {
        return ResponseEntity.ok().body(invoiceService.getInvoicesForClientByStatus(clientId, status));
    }


    @GetMapping(value = "/invoices/clients/{status}", name = "Get all invoices for all client of company based on status as paid/unpaid.  If status is null, get all.")
    public ResponseEntity<InvoiceStatusCompanyDTO> getInvoiceForAllClientByStatus(@PathVariable Contants.PaymentStatus status) {
        return ResponseEntity.ok().body(invoiceService.getInvoicesForAllClientsByStatus(status));
    }

    //TODO: PENDING
    @GetMapping(value = "/invoices/assets/{status}", name = "Get all invoices for all asset of company based on status as paid/unpaid.  If status is null, get all.")
    public ResponseEntity<List<InvoiceStatusDTO>> getInvoiceForAssetByStatus(@PathVariable Contants.PaymentStatus status) {
        return ResponseEntity.ok().body(invoiceService.getInvoicesForAllAssetsByStatus(status));
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
    public DetailedInvoice prepareInvoiceDataForReview(@RequestBody InvoiceMetadata invoiceMetadata) {
        return invoiceService.createInvoiceTemplateFromClient(invoiceMetadata);
    }

    @PostMapping
    public ResponseEntity<Integer> createInvoice(@RequestBody DetailedInvoice detailedInvoice) {
         Invoice invoice = invoiceService.createInvoice(detailedInvoice);
         return ResponseEntity.ok(invoice.getId());
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
