package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class AssetPayableDTOWithStats {
    BigDecimal pendingAmount;
    BigDecimal totalAmount;
    BigDecimal paidAmount;
    List<AssetPayableDTO> payables;
}
