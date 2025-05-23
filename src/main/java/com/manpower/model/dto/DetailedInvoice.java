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
  String clientAddress;
  Integer invoiceId;
  String invoiceNumber;
  LocalDate invoiceDate;
  LocalDate clearedDate;
  LocalDate startDate;
  LocalDate endDate;
  LocalDate dueDate;
  BigDecimal totalAmount; //this is without VAT
  BigDecimal vatAmount;
  BigDecimal totalWithVAT;
  BigDecimal vatRate;
  CompanyDTO company; //this is only to get data back, not to create
  String QRCode;
  private BigDecimal assetPayable;
  private BigDecimal sponsorPayable;
  private BigDecimal profit;


  List<DetailedProjectInvoice> detailedProjectInvoiceList;

}
