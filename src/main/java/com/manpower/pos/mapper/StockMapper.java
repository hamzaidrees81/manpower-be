package com.manpower.pos.mapper;

import com.manpower.pos.dto.StockDto;
import com.manpower.pos.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockDto toDto(Stock stock) {
        if (stock == null) {
            return null;
        }

        StockDto dto = new StockDto();
        dto.setId(stock.getId());
        dto.setProductId(stock.getProduct().getId());
        dto.setQuantity(stock.getQuantity());
        dto.setPrice(stock.getPrice());
        dto.setSupplierId(stock.getSupplier() != null ? stock.getSupplier().getId() : null);
        return dto;
    }

    public Stock toEntity(StockDto dto) {
        if (dto == null) {
            return null;
        }

        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setQuantity(dto.getQuantity());
        stock.setPrice(dto.getPrice());
        // Assuming the product, supplier, and company are fetched or set later
        return stock;
    }
}
