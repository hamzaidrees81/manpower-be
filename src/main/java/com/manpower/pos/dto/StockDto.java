package com.manpower.pos.dto;

import com.manpower.pos.model.Product;
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
    private BigDecimal quantity;
    private BigDecimal retailPrice;
    private BigDecimal minPrice;
    private BigDecimal buyPrice;
    private String storageRack;
    private ShopDTO shop;
}
