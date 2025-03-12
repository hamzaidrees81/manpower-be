package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetailedAssetInvoice {
  Integer assetId;
  String assetName;
  BigDecimal regularHours;
  BigDecimal overtimeHours;
  BigDecimal regularRate;
  BigDecimal overtimeRate;
  BigDecimal totalAmount;
}
