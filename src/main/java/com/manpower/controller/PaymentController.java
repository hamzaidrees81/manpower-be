package com.manpower.controller;

import com.manpower.model.dto.LedgerDTO;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.model.dto.PaymentFilterDTO;
import com.manpower.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.recordPayment(paymentDTO));
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<PaymentDTO>> getByInvoice(@PathVariable Integer invoiceId) {
        return ResponseEntity.ok(paymentService.getPaymentsForInvoice(invoiceId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PaymentDTO>> filterPayments(PaymentFilterDTO filterDTO) {
        List<PaymentDTO> payments = paymentService.filterPayments(filterDTO);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/ledger/filter")
    public ResponseEntity<LedgerDTO> filterLedgerPayments(PaymentFilterDTO filterDTO) {
        List<PaymentDTO> payments = paymentService.filterPayments(filterDTO);

        LedgerDTO.LedgerDTOBuilder ledger = LedgerDTO.builder();
        ledger.payments(payments)
                .totalIncome(BigDecimal.ONE)
                .totalExpense(BigDecimal.TEN)
                .totalAssetExpenses(BigDecimal.ONE)
                .totalCompanyExpenses(BigDecimal.TEN)
                .totalPaidToAssets(BigDecimal.TEN)
                .totalPaidToSponsors(BigDecimal.TEN)
                .profit(BigDecimal.TEN);

        return ResponseEntity.ok(ledger.build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
