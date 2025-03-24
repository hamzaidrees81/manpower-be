package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InvoiceStatusDTO {
  private Integer id;
  private LocalDate creationDate;
  private LocalDate clearedDate;
  private String invoiceNumber;
  private Contants.InvoiceStatus invoiceStatus;
  private BigDecimal payableAmount;
  private LocalDate startDate;
  private LocalDate endDate;
  private BigDecimal taxAmount;
  private BigDecimal totalAmountWithTax;
}
