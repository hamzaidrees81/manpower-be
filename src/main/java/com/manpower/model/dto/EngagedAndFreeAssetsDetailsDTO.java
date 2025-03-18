package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EngagedAndFreeAssetsDetailsDTO {
  private List<AssetDTO> EngagedAssets;
  private List<AssetDTO> FreeAssets;
}
