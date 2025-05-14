package com.manpower.model.dto.stats;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
public class ClientSummaryDTO {
    private Integer clientId;
    private String clientName;

    private int totalProjects;
    private int activeProjects;

    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private BigDecimal totalPaid = BigDecimal.ZERO;
    private BigDecimal outstandingAmount = BigDecimal.ZERO;

    private BigDecimal profit = BigDecimal.ZERO;
    private BigDecimal profitabilityRatio = BigDecimal.ZERO;
}
