package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DetailedInvoice {

  String clientName;
  String invoiceId;
  LocalDate invoiceDate;

  List<DetailedProjectInvoice> detailedProjectInvoiceList;

}
