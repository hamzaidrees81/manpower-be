package com.manpower.model.dto.stats;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class ClientDetailedStatsDTO {
    private Integer clientId;
    private String clientName;

    private int totalProjects;
    private int activeProjects;
    private int totalAssets;

    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private BigDecimal totalReceived = BigDecimal.ZERO;
    private BigDecimal profit = BigDecimal.ZERO;
    private BigDecimal profitabilityRatio = BigDecimal.ZERO;

    private int invoiceCount;
    private int paidInvoices;
    private int unpaidInvoices;
    private int undueInvoices;
    private BigDecimal outstandingAmount = BigDecimal.ZERO;

    private List<ProjectSummaryDTO> projectSummaries;
}
