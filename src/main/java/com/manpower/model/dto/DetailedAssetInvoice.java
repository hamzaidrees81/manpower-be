package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetailedAssetInvoice {
  Integer assetId;
  Integer assetProjectId;
  String assetName;
  BigDecimal regularHours;
  BigDecimal regularRate;
  BigDecimal overtimeHours;
  BigDecimal overtimeRate;
  BigDecimal totalAmount;
  Contants.AssetType assetType;
}
