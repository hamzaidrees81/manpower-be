package com.manpower.model.dto.stats;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatsDTO {
    private Integer projectId;
    private String projectName;
    private String clientName;

    private int totalAssets;
    private int totalInvoices;
    private int paidInvoices;
    private int unpaidInvoices;
    private int dueInvoices;

    private double totalInvoicedBeforeTax;   // sum of totalAmountWithTax
    private double totalInvoicedTax;   // sum of totalAmountWithTax
    private double totalInvoicedWithTax;   // sum of totalAmountWithTax
    private double totalPaidAmount;       // from invoice payments
    private double totalAssetPayable;    // asset payable
    private double totalSponsorPayable;    // asset payable
    private double totalProfit;           // totalPaidAmount - total asset and sponsor payable

    private BigDecimal profitabilityRatio;    // (profit / revenue) * 100

    private List<AssetStatsDTO> assetStats;   // optional: detailed asset-level stats
}
