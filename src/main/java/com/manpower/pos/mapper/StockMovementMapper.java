package com.manpower.pos.mapper;

import com.manpower.pos.dto.PurchaseDTO;
import com.manpower.pos.dto.PurchaseItemDTO;
import com.manpower.pos.dto.StockMovementDto;
import com.manpower.pos.enums.PricingStrategy;
import com.manpower.pos.model.StockMovement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StockMovementMapper {

    public static PurchaseItemDTO toDto(StockMovement smvt) {
        if (smvt == null) {
            return null;
        }

        PurchaseItemDTO dto = PurchaseItemDTO
                .builder()
                .stockMovementId(smvt.getId())
                .productId(smvt.getProduct().getId())
                .batchNo(smvt.getBatch())
                .storageRack(smvt.getStorageRack())
                .buyPrice(smvt.getBuyPrice())
                .minSalePrice(smvt.getMinPrice())
                .VATAmount(smvt.getVatAmount())
                .retailPrice(smvt.getRetailPrice())
                .quantity(smvt.getQuantity())
                .comments(smvt.getComments())
                .pricingStrategy(smvt.getPricingStrategy())
                .build();
        return dto;
    }

//    public StockMovement toEntity(StockMovementDto dto) {
//        if (dto == null) {
//            return null;
//        }
//
//        StockMovement stockMovement = new StockMovement();
//        stockMovement.setId(dto.getId());
//        stockMovement.setQuantity(dto.getQuantity());
//        stockMovement.setRetailPrice(dto.getRetail());
//        stockMovement.setMovementType(dto.getMovementType());
//        stockMovement.setMovementReason(dto.getReason());
//        stockMovement.setMovementDate(dto.getMovementDate());
//        stockMovement.setRelatedEntityId(dto.getRelatedEntityId());
//        stockMovement.setRelatedEntityType(dto.getRelatedEntityType());
//        // Assuming the product and company are set later
//        return stockMovement;
//    }
}
