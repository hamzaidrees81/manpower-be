package com.manpower.pos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaleItemResponseDTO {
    private ProductDto product;
    private Integer saleItemId;
    private Integer productId;
    private String productName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal soldPrice;
    private BigDecimal discount; //amount
    private BigDecimal tax;
    private BigDecimal totalPrice;  // (unitPrice * quantity) - discount + tax
}
