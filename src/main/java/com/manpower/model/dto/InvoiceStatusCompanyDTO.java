package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class InvoiceStatusCompanyDTO {
  BigDecimal totalAmount;
  List<InvoiceStatusDTO> invoiceStatusDTOList;
}
