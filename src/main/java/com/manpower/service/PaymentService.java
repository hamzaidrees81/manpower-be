package com.manpower.service;

import com.manpower.mapper.PaymentMapper;
import com.manpower.model.Account;
import com.manpower.model.Payment;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.model.dto.PaymentFilterDTO;
import com.manpower.repository.AccountRepository;
import com.manpower.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository mainAccountRepository;

    public PaymentDTO recordPayment(Payment payment) {
        Account account = mainAccountRepository.findById(payment.getMainAccount().getId())
            .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().subtract(payment.getAmount()));
        mainAccountRepository.save(account);

        payment.setMainAccount(account);
        Payment payment1 = paymentRepository.save(payment);
        return PaymentMapper.toDTO(payment1);
    }

    public List<PaymentDTO> getPaymentsForInvoice(Integer invoiceId) {
        return paymentRepository.findAllByInvoiceId(invoiceId).stream().map(PaymentMapper::toDTO).collect(Collectors.toList());
    }

    public List<Payment> filterPayments(PaymentFilterDTO filterDTO) {
        return paymentRepository.filterPayments(
                filterDTO.getMainAccountId(),
                filterDTO.getMinAmount(),
                filterDTO.getMaxAmount(),
                filterDTO.getPaymentMethod(),
                filterDTO.getReference(),
                filterDTO.getPaidToType(),
                filterDTO.getPaidToId(),
                filterDTO.getInvoiceId(),
                filterDTO.getStatus(),
                filterDTO.getPaymentType(),
                filterDTO.getRemarks(),
                filterDTO.getStartDate(),
                filterDTO.getEndDate(),
                filterDTO.getStartTimestamp(),
                filterDTO.getEndTimestamp());
    }
}
