package com.manpower.controller;

import com.manpower.mapper.PaymentMapper;
import com.manpower.model.Payment;
import com.manpower.model.dto.PaymentDTO;
import com.manpower.model.dto.PaymentFilterDTO;
import com.manpower.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO payment) {
        return ResponseEntity.ok(paymentService.recordPayment(PaymentMapper.toEntity(payment)));
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
}
