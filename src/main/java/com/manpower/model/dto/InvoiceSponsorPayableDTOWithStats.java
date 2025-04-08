package com.manpower.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class InvoiceSponsorPayableDTOWithStats {
    BigDecimal pendingAmount;
    BigDecimal totalAmount;
    BigDecimal paidAmount;
    List<InvoiceSponsorPayableDTO> payables;
}
