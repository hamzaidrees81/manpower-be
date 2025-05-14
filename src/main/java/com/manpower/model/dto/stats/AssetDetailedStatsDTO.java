package com.manpower.model.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDetailedStatsDTO {
    private Integer assetId;
    private String assetName;
    private String assetType;
    private String status;
    private String assignedToProject;

    private int activeProjects;
    private int totalInvoiceCount;
    private int paidInvoiceCount;
    private int unpaidInvoiceCount;
    private int undueInvoiceCount;

    private Float totalRevenue;
    private Float amountEarned;       // Paid
    private Float upcomingEarning;    // Not due yet
    private Float amountPaid;         // Paid to vendor
    private Float sponsorPayable;
    private Float totalExpenses;
    private Float profitFromAsset;
    private Float profitabilityRatio;


    private LocalDate lastUsedDate;

    private List<ProjectSummaryDTO> projectAssignments;
    private List<InvoiceSummaryDTO> invoices;
    private List<PaymentSummaryDTO> payments;
}
