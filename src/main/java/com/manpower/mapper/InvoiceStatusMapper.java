package com.manpower.mapper;

import com.manpower.common.Contants;
import com.manpower.model.Invoice;
import com.manpower.model.dto.InvoiceStatusDTO;

import java.time.LocalDate;

public class InvoiceStatusMapper {

  public static InvoiceStatusDTO convertToDTO(Invoice invoice) {
    return InvoiceStatusDTO.builder()
      .id(invoice.getId())
      .creationDate(invoice.getCreateDate())
      .clearedDate(invoice.getClearedDate())
      .dueDate(invoice.getDueDate())  // TODO: give due date + some status logic based on this date
      .clientName(invoice.getClient().getName())
      .invoiceNumber(invoice.getNumber())
      .paymentStatus(
              //if due date is not here, return whatever the status is
              invoice.getDueDate()==null ? Contants.PaymentStatus.fromValue(invoice.getStatus()) :
                      //if due date has passed, check if invoice status is pending, then show unpaid, otherwise show whatever the status is (hopefully paid)
                      ((invoice.getDueDate().isBefore(LocalDate.now()) || invoice.getDueDate().isEqual(LocalDate.now())) && Contants.PaymentStatus.fromValue(invoice.getStatus())
                      == Contants.PaymentStatus.INVOICE_PENDING) ?
                      Contants.PaymentStatus.UNPAID : Contants.PaymentStatus.fromValue(invoice.getStatus())
      )
      .payableAmount(invoice.getTotalBeforeTax())
      .startDate(invoice.getStartDate())
      .endDate(invoice.getEndDate())
      .taxAmount(invoice.getTaxAmount())
      .totalAmountWithTax(invoice.getTotalAmountWithTax())
      .build();
  }

}
