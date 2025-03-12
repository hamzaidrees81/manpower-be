package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DetailedInvoice {

  String clientId;
  String clientName;
  String invoiceId;
  LocalDate invoiceDate;
  LocalDate startDate;
  LocalDate endDate;
  BigDecimal totalAmount;

  List<DetailedProjectInvoice> detailedProjectInvoiceList;

}
