package com.manpower.model.dto.stats;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AssetStatsDTO {
    private Integer assetId;
    private String assetName;
    private BigDecimal totalEarning;
    private BigDecimal assetPayable;
    private BigDecimal sponsorPayable;
    private BigDecimal profit;
}
