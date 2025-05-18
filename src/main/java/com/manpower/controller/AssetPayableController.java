package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.model.AssetPayable;
import com.manpower.model.dto.AssetPayableDTO;
import com.manpower.model.dto.AssetPayableDTOWithStats;
import com.manpower.model.dto.InvoiceSponsorPayableDTO;
import com.manpower.service.AssetPayableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/asset-payables")
@RequiredArgsConstructor
public class AssetPayableController {

    private final AssetPayableService assetPayableService;

    @GetMapping
    public ResponseEntity<List<AssetPayableDTO>> getAll() {
        return ResponseEntity.ok(assetPayableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetPayableDTO> getById(@PathVariable Long id) {
        AssetPayableDTO assetPayableDTO =  assetPayableService.findById(id);
        if (assetPayableDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assetPayableDTO);

    }

    @GetMapping("/status/{paymentStatus}/asset")
    public ResponseEntity<AssetPayableDTOWithStats> findPayables(
            @PathVariable Contants.PaymentStatusString paymentStatus,
            @RequestParam(required = false) Integer assetId) {
        return ResponseEntity.ok(assetPayableService.findPayablesWithStats(assetId, paymentStatus));
    }


    @PostMapping
    public ResponseEntity<AssetPayableDTO> create(@RequestBody AssetPayableDTO dto) {
        throw new RuntimeException("Update not implemented yet");
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetPayableDTO> update(@PathVariable Long id, @RequestBody AssetPayableDTO dto) {
        throw new RuntimeException("Update not implemented yet");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assetPayableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
