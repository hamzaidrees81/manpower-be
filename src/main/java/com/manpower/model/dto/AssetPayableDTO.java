package com.manpower.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetPayableDTO {
    private Integer id;
    private Integer assetProjectId;
    private BigDecimal assetPayable;
    private String paymentStatus;
    private Byte status;
    private Integer assetId;
    private Integer invoiceId;
    private BigDecimal paidAmount;
}
