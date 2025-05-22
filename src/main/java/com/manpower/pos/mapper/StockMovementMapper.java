package com.manpower.pos.mapper;

import com.manpower.pos.dto.PurchaseItemDTO;
import com.manpower.pos.dto.SaleItemDTO;
import com.manpower.pos.dto.SaleItemResponseDTO;
import com.manpower.pos.model.StockMovement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class StockMovementMapper {

    private final ProductMapper productMapper;

    public PurchaseItemDTO toPurchaseDto(StockMovement smvt) {
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
                .vatAmount(smvt.getVatAmount())
                .retailPrice(smvt.getRetailPrice())
                .quantity(smvt.getQuantity())
                .comments(smvt.getComments())
                .pricingStrategy(smvt.getPricingStrategy())
                .build();
        return dto;
    }

    public SaleItemResponseDTO toSaleDTO(StockMovement smvt) {
        if (smvt == null) {
            return null;
        }


        SaleItemResponseDTO dto = SaleItemResponseDTO
                .builder()
                .saleItemId(smvt.getId())
                .productId(smvt.getProduct().getId())
                .quantity(smvt.getQuantity())
                .unitPrice(smvt.getRetailPrice())
                .soldPrice(smvt.getSoldPrice())
                .totalPrice(smvt.getRetailPrice().multiply(smvt.getQuantity()))
                .discount(smvt.getRetailPrice().subtract(smvt.getSoldPrice()))
                .tax(smvt.getVatAmount())
                .product(productMapper.toDto(smvt.getProduct()))
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
