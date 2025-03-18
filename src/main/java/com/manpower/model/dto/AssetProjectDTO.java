package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AssetProjectDTO {

    private Integer id;
    private Integer assetId;     // Instead of full Asset, referencing just the ID
    private Integer projectId;   // Instead of full Project, referencing just the ID
    private String projectName;  // Assuming Project has a 'name' field
    private String designationName;  // Assuming Designation has a 'name' field
    private Integer designationId;
    private String assetProjectName;
    private BigDecimal regularRate;
    private BigDecimal overtimeRate;
    private BigDecimal regularRatePaid;
    private BigDecimal overtimeRatePaid;
    private LocalDate startDate;
    private LocalDate endDate;
    private Contants.AssetProjectStatus isActive;
    private Contants.Status status;
}
