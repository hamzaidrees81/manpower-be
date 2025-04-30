package com.manpower.pos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleItemDTO {
    private Integer productId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private BigDecimal tax;
}
