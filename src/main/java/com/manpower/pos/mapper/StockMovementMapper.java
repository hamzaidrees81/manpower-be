package com.manpower.pos.mapper;

import com.manpower.pos.dto.StockMovementDto;
import com.manpower.pos.model.StockMovement;
import org.springframework.stereotype.Component;

@Component
public class StockMovementMapper {

    public StockMovementDto toDto(StockMovement stockMovement) {
        if (stockMovement == null) {
            return null;
        }

        StockMovementDto dto = new StockMovementDto();
        dto.setId(stockMovement.getId());
        dto.setProductId(stockMovement.getProduct().getId());
        dto.setQuantity(stockMovement.getQuantity());
        dto.setPrice(stockMovement.getPrice());
        dto.setMovementType(stockMovement.getMovementType());
        dto.setReason(stockMovement.getReason());
        dto.setMovementDate(stockMovement.getMovementDate());
        dto.setRelatedEntityId(stockMovement.getRelatedEntityId());
        dto.setRelatedEntityType(stockMovement.getRelatedEntityType());
        return dto;
    }

    public StockMovement toEntity(StockMovementDto dto) {
        if (dto == null) {
            return null;
        }

        StockMovement stockMovement = new StockMovement();
        stockMovement.setId(dto.getId());
        stockMovement.setQuantity(dto.getQuantity());
        stockMovement.setPrice(dto.getPrice());
        stockMovement.setMovementType(dto.getMovementType());
        stockMovement.setReason(dto.getReason());
        stockMovement.setMovementDate(dto.getMovementDate());
        stockMovement.setRelatedEntityId(dto.getRelatedEntityId());
        stockMovement.setRelatedEntityType(dto.getRelatedEntityType());
        // Assuming the product and company are set later
        return stockMovement;
    }
}
