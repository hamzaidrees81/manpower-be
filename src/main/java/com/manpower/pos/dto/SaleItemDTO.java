package com.manpower.pos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaleItemDTO {
    private Integer productId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal soldPrice;
    private BigDecimal totalPrice; //not using, calculated
    private BigDecimal discount; //not using, calculated
    private BigDecimal tax;
}
