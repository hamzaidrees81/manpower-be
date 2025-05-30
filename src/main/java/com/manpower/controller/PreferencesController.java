package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.model.dto.InvoiceStatusCompanyDTO;
import com.manpower.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RestController("/api/preferences")
public class PreferencesController {

    private final PreferenceService preferenceService;

    @GetMapping(value = "/tax", name = "Get tax percentage")
    public ResponseEntity<BigDecimal> getTax() {
        return ResponseEntity.ok().body(preferenceService.findVATAmount());
    }

    @GetMapping(value = "/erpInvNo", name = "Get ERP invoice number")
    public ResponseEntity<Integer> getERPInvoiceNumber() {
        return ResponseEntity.ok().body(preferenceService.erpInvNo());
    }
    @GetMapping(value = "/saleInvNo", name = "Get Sale invoice number")
    public ResponseEntity<Integer> getPOSInvoiceNumber() {
        return ResponseEntity.ok().body(preferenceService.getPOSInvNO());
    }
}
