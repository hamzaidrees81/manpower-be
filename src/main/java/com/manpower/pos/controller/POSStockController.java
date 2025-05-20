package com.manpower.pos.controller;

import com.manpower.pos.dto.StockDto;
import com.manpower.pos.enums.StocksForPage;
import com.manpower.pos.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/api/stocks")
@RequiredArgsConstructor
public class POSStockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockDto>> getStocks(
            @RequestParam(required = false) Integer shopId,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) StocksForPage stocksForPage) {
        return ResponseEntity.ok(stockService.findStocks(shopId, productId, stocksForPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable Integer id) {
        return stockService.getStockById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        return ResponseEntity.ok(stockService.createStock(stockDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDto> updateStock(@PathVariable Integer id, @RequestBody StockDto stockDto) {
        return stockService.updateStock(id, stockDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        if (stockService.deleteStock(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

//    // Stock Movement Endpoints
//
//    @PostMapping("/movements")
//    public ResponseEntity<StockMovementDto> createStockMovement(@RequestBody StockMovementDto stockMovementDto) {
//        return ResponseEntity.ok(stockService.createStockMovement(stockMovementDto));
//    }
}
