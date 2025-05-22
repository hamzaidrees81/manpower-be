package com.manpower.pos.dto;

import com.manpower.pos.enums.AliveStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class SaleFilterRequest {
    private Integer clientId;
    private Integer companyId;
    private Integer shopId;
    private AliveStatus status;
    private Instant startDate;
    private Instant endDate;
    private BigDecimal minTotalAmount;
}
