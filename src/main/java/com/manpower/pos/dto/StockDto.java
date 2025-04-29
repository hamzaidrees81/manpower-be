package com.manpower.pos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockDto {
    private Integer id;
    private Integer productId;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer supplierId;
}
