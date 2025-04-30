package com.manpower.pos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleItemResponseDTO {
    private Integer productId;
    private String productName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private BigDecimal tax;
    private BigDecimal totalPrice;  // (unitPrice * quantity) - discount + tax
}
