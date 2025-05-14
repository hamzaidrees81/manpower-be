package com.manpower.model.dto.stats;

import java.math.BigDecimal;
import java.util.List;

public class ClientStatsDTO {
    private Integer clientId;
    private String clientName;

    private int totalProjects;
    private int activeProjects;
    private int totalAssets;

    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private BigDecimal totalPaid = BigDecimal.ZERO;
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    private BigDecimal profit = BigDecimal.ZERO;
    private BigDecimal profitabilityRatio = BigDecimal.ZERO;

    private int invoiceCount;
    private int paidInvoices;
    private int unpaidInvoices;
    private int overdueInvoices;
    private BigDecimal outstandingAmount = BigDecimal.ZERO;

    private List<ProjectSummaryDTO> projectSummaries;

    // Getters and Setters
}
