package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceSponsorPayableDTO {
    private Integer id;
    private Integer projectSponsorshipId;
    private BigDecimal sponsorshipPayable;
    private Contants.PaymentStatusString paymentStatus;
    private Byte status;
    private Integer sponsorshipAssetId;
    private String sponsorshipAssetName;
    private String sponsorshipDeterminant;
    private Integer sponsorId;
    private String sponsorName;
    private Integer invoiceId;
    private BigDecimal paidAmount;
}
