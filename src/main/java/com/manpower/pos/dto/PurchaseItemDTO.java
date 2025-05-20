package com.manpower.pos.dto;

import com.manpower.pos.enums.PricingStrategy;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PurchaseItemDTO {
        private Integer stockMovementId;
        private Integer productId;
        private String batchNo;
        private String storageRack;
        private BigDecimal buyPrice;
        private BigDecimal minSalePrice;
        private BigDecimal retailPrice;
        private BigDecimal quantity;
        private String comments;
        private PricingStrategy pricingStrategy;
}