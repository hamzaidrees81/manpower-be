package com.manpower.model.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSummaryDTO {
    private Integer projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // Ongoing, Completed, etc.
}
