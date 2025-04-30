package com.manpower.pos.controller;

import com.manpower.pos.dto.SaleRequestDTO;
import com.manpower.pos.dto.SaleResponseDTO;
import com.manpower.pos.model.Sale;
import com.manpower.pos.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class POSSaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleRequestDTO dto) {
        SaleResponseDTO sale = saleService.createSale(dto);
        return ResponseEntity.ok(sale);
    }
}
