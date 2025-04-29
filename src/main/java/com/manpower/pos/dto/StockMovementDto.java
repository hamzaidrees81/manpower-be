package com.manpower.pos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class StockMovementDto {
    private Integer id;
    private Integer productId;
    private BigDecimal quantity;
    private BigDecimal price;
    private String movementType;
    private String reason;
    private Instant movementDate;
    private Integer relatedEntityId;
    private String relatedEntityType;
}
