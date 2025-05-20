package com.manpower.pos.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class StockMovementDto {
    private Integer id;
    private Integer productId;
    private BigDecimal quantity;
    private BigDecimal retail;
    private String movementType;
    private String reason;
    private Instant movementDate;
    private Integer relatedEntityId;
    private String relatedEntityType;
}
