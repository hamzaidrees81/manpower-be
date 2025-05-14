package com.manpower.model.dto.stats;

import com.manpower.common.Contants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetGeneralSummaryDTO {
    private Integer assetId;
    private String assetName;
    private Contants.EngagementStatus status; // e.g.,    ENGAGED,FREE
    private int activeProjects;
    private BigDecimal revenueEarned;
    private BigDecimal expenses;
    private BigDecimal profitFromAsset;
    private LocalDate lastUsedDate;

}
