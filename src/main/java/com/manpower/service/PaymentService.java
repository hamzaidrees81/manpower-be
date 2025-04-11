package com.manpower.service;

import com.manpower.common.PaymentConstant;
import com.manpower.mapper.PaymentMapper;
import com.manpower.model.*;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.model.dto.PaymentFilterDTO;
import com.manpower.repository.AccountRepository;
import com.manpower.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository mainAccountRepository;
    private final AssetService assetService;
    private final ExpenseService expenseService;
    private final SponsorService sponsorService;
    private final InvoiceService invoiceService;

    public PaymentDTO recordPayment(PaymentDTO paymentDTO) {
        Account account = mainAccountRepository.findById(paymentDTO.getMainAccountId())
            .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().subtract(paymentDTO.getAmount()));
        mainAccountRepository.save(account);

        Payment payment = PaymentMapper.toEntity(paymentDTO,account);
        Payment payment1 = paymentRepository.save(payment);

        Asset asset = null;
        Sponsor sponsor = null;
        Expense expense = null;
        Invoice invoice = null;

        //if PAID_TO is asset, sponsor, or invoice
        PaymentConstant.PaidToType paidToType = PaymentConstant.PaidToType.valueOf(payment.getPaidToType());

        switch (paidToType) {
            case PaymentConstant.PaidToType.ASSET ->
                    asset = assetService.getAssetById(payment1.getPaidToId()).get();
            case PaymentConstant.PaidToType.EXPENSE ->
                    expense = expenseService.getExpenseById(payment1.getPaidToId()).get();
            case PaymentConstant.PaidToType.SPONSOR ->
                    sponsor = sponsorService.getSponsorById(payment1.getPaidToId()).get();
            case PaymentConstant.PaidToType.INVOICE ->
                    invoice = invoiceService.getInvoiceById(payment1.getPaidToId()).get();
        };

        return PaymentMapper.toDTO(payment1, asset, sponsor, expense, invoice);
    }

    public List<PaymentDTO> getPaymentsForInvoice(Integer invoiceId) {
        return paymentRepository.findAllByInvoiceId(invoiceId).stream().map(PaymentMapper::toDTO).collect(Collectors.toList());
    }

    public List<PaymentDTO> filterPayments(PaymentFilterDTO filterDTO) {
        List<Payment>   payments = paymentRepository.filterPayments(
                filterDTO.getMainAccountId(),
                filterDTO.getMinAmount(),
                filterDTO.getMaxAmount(),
                filterDTO.getPaymentMethod() != null  ? filterDTO.getPaymentMethod().name() : null,
                filterDTO.getReference(),
                filterDTO.getPaidToType() != null  ? filterDTO.getPaidToType().name() : null,
                filterDTO.getPaidToId(),
                filterDTO.getInvoiceId(),
                filterDTO.getStatus() != null  ? filterDTO.getStatus().name() : null,
                filterDTO.getPaymentType()  != null  ? filterDTO.getPaymentType().name() : null,
                filterDTO.getRemarks(),
                filterDTO.getStartDate(),
                filterDTO.getEndDate(),
                filterDTO.getStartTimestamp(),
                filterDTO.getEndTimestamp());

        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment payment : payments) {

            Asset asset = null;
            Sponsor sponsor = null;
            Expense expense = null;
            Invoice invoice = null;

            //if PAID_TO is asset, sponsor, or invoice
            PaymentConstant.PaidToType paidToType = PaymentConstant.PaidToType.valueOf(payment.getPaidToType());

            switch (paidToType) {
                case PaymentConstant.PaidToType.ASSET ->
                        asset = assetService.getAssetById(payment.getPaidToId()).get();
                case PaymentConstant.PaidToType.EXPENSE ->
                        expense = expenseService.getExpenseById(payment.getPaidToId()).get();
                case PaymentConstant.PaidToType.SPONSOR ->
                        sponsor = sponsorService.getSponsorById(payment.getPaidToId()).get();
                case PaymentConstant.PaidToType.INVOICE ->
                        invoice = invoiceService.getInvoiceById(payment.getPaidToId()).get();
            };
            paymentDTOS.add(PaymentMapper.toDTO(payment, asset, sponsor, expense, invoice));
        }

        return paymentDTOS;
    }
}
