package com.manpower.pos.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    private Integer id;
    private Integer productId;
    private ProductDto product;
    private BigDecimal stockQty;
    private BigDecimal retailPrice;
    private BigDecimal minPrice;
    private BigDecimal buyPrice;
    private BigDecimal discount;
    private BigDecimal tax;
    private String storageRack;
    private ShopDTO shop;
}
