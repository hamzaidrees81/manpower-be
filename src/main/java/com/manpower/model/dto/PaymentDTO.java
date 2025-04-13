package com.manpower.model.dto;

import com.manpower.common.PaymentConstant;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class PaymentDTO {
    private Integer id;
    private Integer mainAccountId;
    private String mainAccountName;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private PaymentConstant.PaymentMethod paymentMethod;
    private String reference;
    private PaymentConstant.PaidToType paidToType;
    private Integer paidToId;
    private String paidToName;
    private Integer invoiceId;
    private Integer invoiceName;
    private String remarks;
    private PaymentConstant.PaymentStatus status;
    private PaymentConstant.PaymentType paymentType;
    private PaymentConstant.PaymentDirection paymentDirection;
    private Instant paymentTimestamp;
    private Instant createdAt;
    private Instant updatedAt;
}