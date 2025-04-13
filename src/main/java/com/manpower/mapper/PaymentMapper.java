package com.manpower.mapper;

import com.manpower.common.PaymentConstant;
import com.manpower.model.*;
import com.manpower.model.dto.PaymentDTO;

import java.time.Instant;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment payment) {
        if (payment == null) return null;

        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setMainAccountId(payment.getMainAccount() != null ? payment.getMainAccount().getId() : null);
        dto.setMainAccountName(payment.getMainAccount() != null ? payment.getMainAccount().getName() : null);
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentMethod(PaymentConstant.PaymentMethod.valueOf(payment.getPaymentMethod()));
        dto.setReference(payment.getReference());
        dto.setPaidToType(PaymentConstant.PaidToType.valueOf(payment.getPaidToType()));
        dto.setPaidToId(payment.getPaidToId());
        dto.setInvoiceId(payment.getInvoiceId());
        dto.setRemarks(payment.getRemarks());
        dto.setStatus(PaymentConstant.PaymentStatus.valueOf(payment.getStatus()));
        dto.setPaymentType(PaymentConstant.PaymentType.valueOf(payment.getPaymentType()));
        dto.setPaymentDirection(PaymentConstant.PaymentDirection.valueOf(payment.getPaymentDirection()));
        dto.setPaymentTimestamp(payment.getPaymentTimestamp());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());
        return dto;
    }

    public static PaymentDTO toDTO(Payment payment, Asset asset, Sponsor sponsor, Expense expense, Invoice invoice) {
        if (payment == null) return null;

        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setMainAccountId(payment.getMainAccount() != null ? payment.getMainAccount().getId() : null);
        dto.setMainAccountName(payment.getMainAccount() != null ? payment.getMainAccount().getName() : null);
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentMethod(PaymentConstant.PaymentMethod.valueOf(payment.getPaymentMethod()));
        dto.setReference(payment.getReference());
        dto.setPaidToType(PaymentConstant.PaidToType.valueOf(payment.getPaidToType()));
        dto.setPaidToId(payment.getPaidToId());
        dto.setInvoiceId(payment.getInvoiceId());
        dto.setRemarks(payment.getRemarks());
        dto.setStatus(PaymentConstant.PaymentStatus.valueOf(payment.getStatus()));
        dto.setPaymentType(PaymentConstant.PaymentType.valueOf(payment.getPaymentType()));
        dto.setPaymentDirection(PaymentConstant.PaymentDirection.valueOf(payment.getPaymentDirection()));
        dto.setPaymentTimestamp(payment.getPaymentTimestamp());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());

        //if PAID_TO is asset, sponsor, or invoice
        PaymentConstant.PaidToType paidToType = PaymentConstant.PaidToType.valueOf(payment.getPaidToType());

        String paymentName = switch (paidToType) {
            case PaymentConstant.PaidToType.ASSET -> "Asset - " + asset.getName();
            case PaymentConstant.PaidToType.EXPENSE -> "Expense - " + expense.getExpenseCategory();
            case PaymentConstant.PaidToType.SPONSOR -> "Sponsor - " + sponsor.getName();
            case PaymentConstant.PaidToType.INVOICE -> "Invoice - " + invoice.getNumber();
            case PaymentConstant.PaidToType.OTHER -> "";
        };

        dto.setPaidToName(paymentName);
        return dto;
    }

    public static Payment toEntity(PaymentDTO dto) {
        if (dto == null) return null;

        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod().name());
        payment.setReference(dto.getReference());
        payment.setPaidToType(dto.getPaidToType().name());
        payment.setPaidToId(dto.getPaidToId());
        payment.setInvoiceId(dto.getInvoiceId());
        payment.setRemarks(dto.getRemarks());
        payment.setStatus(dto.getStatus().name());
        payment.setPaymentType(dto.getPaymentType().name());
        payment.setPaymentTimestamp(dto.getPaymentTimestamp());
        payment.setPaymentDirection(dto.getPaymentDirection() != null ?
                dto.getPaymentDirection().name() : PaymentConstant.PaymentDirection.OUTGOING.name());
        payment.setCreatedAt(dto.getCreatedAt());
        payment.setUpdatedAt(dto.getUpdatedAt());

        return payment;
    }

    public static Payment toEntity(PaymentDTO dto, Account account) {
        if (dto == null) return null;

        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setMainAccount(account);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod().name());
        payment.setReference(dto.getReference());
        payment.setPaidToType(dto.getPaidToType().name());
        payment.setPaidToId(dto.getPaidToId());
        payment.setInvoiceId(dto.getInvoiceId());
        payment.setRemarks(dto.getRemarks());
        payment.setStatus(dto.getStatus().name());
        payment.setPaymentType(dto.getPaymentType().name());
        payment.setPaymentTimestamp(Instant.now());
        payment.setCreatedAt(dto.getCreatedAt());
        payment.setUpdatedAt(dto.getUpdatedAt());
        payment.setPaymentDirection(dto.getPaymentDirection() != null ?
                dto.getPaymentDirection().name() : PaymentConstant.PaymentDirection.OUTGOING.name());

        return payment;
    }
}
