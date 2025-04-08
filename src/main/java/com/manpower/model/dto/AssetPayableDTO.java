package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetPayableDTO {
    private Integer id;
    private Integer assetProjectId;
    private BigDecimal assetPayable;
    private Contants.PaymentStatusString paymentStatus;
    private Byte status;
    private Integer assetId;
    private Integer invoiceId;
    private BigDecimal paidAmount;
}
