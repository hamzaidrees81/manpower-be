package com.manpower.pos.dto;

import com.manpower.pos.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockDto {
    private Integer id;
    private Integer productId;
    private ProductDto product;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer supplierId;
    private SupplierDTO supplier;
}
