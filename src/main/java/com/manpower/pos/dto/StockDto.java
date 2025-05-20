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
    private BigDecimal price;
    private Integer supplierId;
    private SupplierDTO supplier;
}
