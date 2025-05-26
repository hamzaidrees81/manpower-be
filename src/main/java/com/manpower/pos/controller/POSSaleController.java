package com.manpower.pos.controller;

import com.manpower.pos.dto.*;
import com.manpower.pos.model.Sale;
import com.manpower.pos.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/api/sales")
@RequiredArgsConstructor
public class POSSaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<Integer> createSale(@RequestBody SaleRequestDTO dto) {
        SaleResponseDTO sale = saleService.createSale(dto);
        return ResponseEntity.ok(sale.getId());
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleResponseDTO> getSale(@RequestParam Integer saleId) throws Exception {
        return ResponseEntity.ok().body(saleService.getSale(saleId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SaleResponseDTO>> filterSales(@ModelAttribute SaleFilterRequest dto) {
        return ResponseEntity.ok().body(saleService.filterSales(dto));
    }

//    @GetMapping("/filter")
//    public ResponseEntity<List<PurchaseDTO>> filterPurchases(@ModelAttribute PurchaseFilterDTO dto) {
//        List<PurchaseDTO> filtered = purchaseService.filterPurchases(dto);
//        return ResponseEntity.ok(filtered);
//    }

}
