package com.manpower.pos.controller;

import com.manpower.pos.dto.PurchaseDTO;
import com.manpower.pos.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pos/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Integer> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return ResponseEntity.ok().body(purchaseService.addPurchase(purchaseDTO));
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestParam Integer purchaseId) {
        return ResponseEntity.ok().body(purchaseService.getPurchase(purchaseId));
    }
}
