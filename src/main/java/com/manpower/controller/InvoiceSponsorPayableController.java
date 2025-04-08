package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.model.dto.AssetPayableDTO;
import com.manpower.model.dto.InvoiceSponsorPayableDTO;
import com.manpower.model.dto.InvoiceSponsorPayableDTOWithStats;
import com.manpower.service.InvoiceSponsorPayableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-sponsor-payables")
@RequiredArgsConstructor
public class InvoiceSponsorPayableController {

    private final InvoiceSponsorPayableService invoiceSponsorPayableService;

    @GetMapping
    public ResponseEntity<List<InvoiceSponsorPayableDTO>> getAll() {
        return ResponseEntity.ok(invoiceSponsorPayableService.findAll());
    }

    @GetMapping("status/{paymentStatus}/sponsor")
    public ResponseEntity<InvoiceSponsorPayableDTOWithStats> findPayables(
            @PathVariable Contants.PaymentStatusString paymentStatus,
            @RequestParam(required = false) Integer sponsorId) {
        return ResponseEntity.ok(invoiceSponsorPayableService.findPayablesWithStats(sponsorId, paymentStatus));
    }


    @GetMapping("/{id}")
    public ResponseEntity<InvoiceSponsorPayableDTO> getById(@PathVariable Long id) {
        InvoiceSponsorPayableDTO sponsorPayableDTO = invoiceSponsorPayableService.findById(id);
        if (sponsorPayableDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sponsorPayableDTO);
    }

    @PostMapping
    public ResponseEntity<InvoiceSponsorPayableDTO> create(@RequestBody InvoiceSponsorPayableDTO dto) {
        throw new RuntimeException("Update not implemented yet");
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceSponsorPayableDTO> update(@PathVariable Long id, @RequestBody InvoiceSponsorPayableDTO dto) {
        throw new RuntimeException("Update not implemented yet");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        invoiceSponsorPayableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
