package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EngagedAndFreeAssetsCountDTO {
  private Long engagedAssetsCount;
  private Long freeAssetsCount;
}
