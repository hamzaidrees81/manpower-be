package com.manpower.mapper;

import com.manpower.common.PaymentConstant;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.model.Account;
import com.manpower.model.Payment;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment payment) {
        if (payment == null) return null;

        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setMainAccountId(payment.getMainAccount() != null ? payment.getMainAccount().getId() : null);
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
        dto.setPaymentTimestamp(payment.getPaymentTimestamp());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());

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
        payment.setPaymentTimestamp(dto.getPaymentTimestamp());
        payment.setCreatedAt(dto.getCreatedAt());
        payment.setUpdatedAt(dto.getUpdatedAt());

        return payment;
    }
}
