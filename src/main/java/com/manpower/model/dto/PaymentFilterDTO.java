package com.manpower.model.dto;

import com.manpower.common.PaymentConstant;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentFilterDTO {
    private Integer mainAccountId;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private PaymentConstant.PaymentMethod paymentMethod;
    private String reference;
    private PaymentConstant.PaidToType paidToType;
    private Integer paidToId;
    private Integer invoiceId;
    private String remarks;
    private PaymentConstant.PaymentStatus status;
    private PaymentConstant.PaymentType paymentType;
    private PaymentConstant.PaymentDirection paymentDirection;
    private LocalDate startDate;
    private LocalDate endDate;
    private Instant startTimestamp;
    private Instant endTimestamp;
}
