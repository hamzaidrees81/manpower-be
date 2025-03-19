package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InvoiceStatusDTO {
  LocalDate creationDate;
  LocalDate clearedDate;
  String invoiceNumber;
  Contants.InvoiceStatus invoiceStatus;
  BigDecimal payableAmount;
}
