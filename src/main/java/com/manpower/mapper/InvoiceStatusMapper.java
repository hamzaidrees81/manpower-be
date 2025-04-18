package com.manpower.mapper;

import com.manpower.common.Contants;
import com.manpower.model.Invoice;
import com.manpower.model.dto.InvoiceStatusDTO;

public class InvoiceStatusMapper {

  public static InvoiceStatusDTO convertToDTO(Invoice invoice) {
    return InvoiceStatusDTO.builder()
      .id(invoice.getId())
      .creationDate(invoice.getCreateDate())
      .clearedDate(invoice.getClearedDate())
      .invoiceNumber(invoice.getNumber())
      .paymentStatus(invoice.getStatus() == 1 ? Contants.PaymentStatus.UNPAID : Contants.PaymentStatus.PAID)
      .payableAmount(invoice.getTotalBeforeTax())
      .startDate(invoice.getStartDate())
      .endDate(invoice.getEndDate())
      .taxAmount(invoice.getTaxAmount())
      .totalAmountWithTax(invoice.getTotalAmountWithTax())
      .build();
  }

}
