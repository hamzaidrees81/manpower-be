package com.manpower.pos.service;

import com.manpower.pos.dto.StockDto;
import com.manpower.pos.dto.StockMovementDto;
import com.manpower.pos.mapper.StockMapper;
import com.manpower.pos.mapper.StockMovementMapper;
import com.manpower.pos.model.Stock;
import com.manpower.pos.model.StockMovement;
import com.manpower.pos.repository.StockRepository;
import com.manpower.pos.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockMovementRepository stockMovementRepository;
    private final StockMapper stockMapper;
    private final StockMovementMapper stockMovementMapper;

    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(stockMapper::toDto)
                .toList();
    }

    public Optional<StockDto> getStockById(Integer id) {
        return stockRepository.findById(Long.valueOf(id))
                .map(stockMapper::toDto);
    }

    public StockDto createStock(StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        Stock saved = stockRepository.save(stock);
        return stockMapper.toDto(saved);
    }

    public Optional<StockDto> updateStock(Integer id, StockDto stockDto) {
        return stockRepository.findById(Long.valueOf(id))
                .map(existingStock -> {
                    Stock updatedStock = stockMapper.toEntity(stockDto);
                    updatedStock.setId(id);
                    Stock saved = stockRepository.save(updatedStock);
                    return stockMapper.toDto(saved);
                });
    }

    public boolean deleteStock(Integer id) {
        if (stockRepository.existsById(Long.valueOf(id))) {
            stockRepository.deleteById(Long.valueOf(id));
            return true;
        }
        return false;
    }

    // Stock Movement Methods

    public List<StockMovementDto> getAllStockMovements() {
        List<StockMovement> stockMovements = stockMovementRepository.findAll();
        return stockMovements.stream()
                .map(stockMovementMapper::toDto)
                .toList();
    }

    public StockMovementDto createStockMovement(StockMovementDto stockMovementDto) {
        StockMovement stockMovement = stockMovementMapper.toEntity(stockMovementDto);
        StockMovement savedMovement = stockMovementRepository.save(stockMovement);

        // Update stock quantity based on the movement
        updateStockQuantity(stockMovement);

        return stockMovementMapper.toDto(savedMovement);
    }

    private void updateStockQuantity(StockMovement stockMovement) {
        Optional<Stock> stockOptional = stockRepository.findByProduct_Id(stockMovement.getProduct().getId());
        stockOptional.ifPresent(stock -> {
            if ("IN".equals(stockMovement.getMovementType())) {
                stock.setQuantity(stock.getQuantity().add(stockMovement.getQuantity()));
            } else if ("OUT".equals(stockMovement.getMovementType())) {
                stock.setQuantity(stock.getQuantity().subtract(stockMovement.getQuantity()));
            }
            stockRepository.save(stock);
        });
    }
}
