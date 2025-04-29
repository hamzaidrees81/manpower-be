package com.manpower.service;

import com.manpower.common.PaymentConstant;
import com.manpower.common.Contants;
import com.manpower.mapper.PaymentMapper;
import com.manpower.model.*;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.model.dto.PaymentFilterDTO;
import com.manpower.repository.AccountRepository;
import com.manpower.repository.InvoiceRepository;
import com.manpower.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository mainAccountRepository;
    private final AssetService assetService;
    private final SponsorService sponsorService;
    private final InvoiceService invoiceService;
    private final InvoiceRepository invoiceRepository;
    private final ExpenseCategoryService expenseCategoryService;

    @Transactional
    public PaymentDTO recordPayment(PaymentDTO paymentDTO) {
        Account account = mainAccountRepository.findById(paymentDTO.getMainAccountId())
            .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().subtract(paymentDTO.getAmount()));
        mainAccountRepository.save(account);

        boolean clearInvoice = false;
        Invoice invoiceUnderPayment = null;

        //if it is invoice, see if it is paid or not...
        if(paymentDTO.getPaidToType().equals(PaymentConstant.PaidToType.INVOICE)) {

            invoiceUnderPayment = invoiceRepository.findById(paymentDTO.getInvoiceId()).orElseThrow(() -> new RuntimeException("Invoice not found"));

            //find total of payments already made for this invoice
            List<Payment> invoicePayments = paymentRepository.findAllByInvoiceId(paymentDTO.getInvoiceId());
            //find total paid
            BigDecimal totalPaid = invoicePayments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            //if after this payment, invoice will be cleared
            if (totalPaid.add(paymentDTO.getAmount()).compareTo(invoiceUnderPayment.getTotalAmountWithTax()) >= 0) {
                //clear the invoice
                clearInvoice = true;
            }
        }

        Payment payment = PaymentMapper.toEntity(paymentDTO,account);
        Payment payment1 = paymentRepository.save(payment);

        if(clearInvoice) {
            invoiceUnderPayment.setStatus(Contants.PaymentStatus.PAID.getValue());
            invoiceRepository.save(invoiceUnderPayment);

            //now for assets and sponsors, change their payment type to unpaid...
//            TODO
        }

        Asset asset = null;
        Sponsor sponsor = null;
        ExpenseCategory expenseCategory = null;
        Invoice invoice = null;

        //if PAID_TO is asset, sponsor, or invoice
        PaymentConstant.PaidToType paidToType = PaymentConstant.PaidToType.valueOf(payment.getPaidToType());

        switch (paidToType) {
            case ASSET ->
                    asset = assetService.getAssetById(payment1.getPaidToId()).get();
            case EXPENSE ->
                    {
                    // expenseCategory = expenseService.getExpenseById(payment1.getPaidToId()).get();
                    //IN CASE OF TYPE EXPENSE, INVOICE ID will be EXPENSE_CATEGORY
                    expenseCategory = expenseCategoryService.getExpenseCategoryById(Long.valueOf(payment1.getInvoiceId())).get();
                    //if paid to is selected as an asset, add it too
                    if(payment1.getPaidToId() !=null)
                        asset = assetService.getAssetById(payment1.getPaidToId()).get();

                    }
            case SPONSOR ->
                    sponsor = sponsorService.getSponsorById(payment1.getPaidToId()).get();
            case INVOICE ->
                    invoice = invoiceService.getInvoiceById(payment1.getInvoiceId()).get();
        };

        return PaymentMapper.toDTO(payment1, asset, sponsor, expenseCategory, invoice);
    }

    public List<PaymentDTO> getPaymentsForInvoice(Integer invoiceId) {
        return paymentRepository.findAllByInvoiceId(invoiceId).stream().map(PaymentMapper::toDTO).collect(Collectors.toList());
    }

    public List<PaymentDTO> filterPayments(PaymentFilterDTO filterDTO) {
        List<Payment>   payments = paymentRepository.filterPayments( //TODO: BRING BOTH INVOICE AND INVOICES...
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
                filterDTO.getEndTimestamp(),
                filterDTO.getPaymentDirection() != null ? filterDTO.getPaymentDirection().name() : null
        );

        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment payment : payments) {

            Asset asset = null;
            Sponsor sponsor = null;
            ExpenseCategory expenseCategory = null;
            Invoice invoice = null;

            //if PAID_TO is asset, sponsor, or invoice
            PaymentConstant.PaidToType paidToType = PaymentConstant.PaidToType.valueOf(payment.getPaidToType());

            switch (paidToType) {
                case ASSET ->
                        asset = assetService.getAssetById(payment.getPaidToId()).get();
                case EXPENSE ->
                {
                    // expenseCategory = expenseService.getExpenseById(payment1.getPaidToId()).get();
                    //IN CASE OF TYPE EXPENSE, INVOICE ID will be EXPENSE_CATEGORY
                    expenseCategory = expenseCategoryService.getExpenseCategoryById(Long.valueOf(payment.getInvoiceId())).get();
                    //if paid to is selected as an asset, add it too
                    if(payment.getPaidToId() !=null)
                        asset = assetService.getAssetById(payment.getPaidToId()).get();

                }
                case SPONSOR ->
                        sponsor = sponsorService.getSponsorById(payment.getPaidToId()).get();
                case INVOICE ->
                        //take invoice from invoice id
//                        invoice = invoiceService.getInvoiceById(payment.getPaidToId()).get();
                        invoice = invoiceService.getInvoiceById(payment.getInvoiceId()).get();
            };
            paymentDTOS.add(PaymentMapper.toDTO(payment, asset, sponsor, expenseCategory, invoice));
        }

        return paymentDTOS;
    }

    public void deletePayment(Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(PaymentConstant.PaymentStatus.DELETED.name());
        paymentRepository.save(payment);
    }
}
