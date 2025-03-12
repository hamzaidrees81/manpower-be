package com.manpower.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DetailedInvoice {

  String clientName;
  String invoiceId;

  List<DetailedProjectInvoice> detailedProjectInvoiceList;

}
