package com.manpower.model.dto.stats;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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
