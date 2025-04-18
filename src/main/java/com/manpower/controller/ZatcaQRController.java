package com.manpower.controller;

import com.manpower.util.QRCodeGenerator;
import com.manpower.util.TLVUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ZatcaQRController {

    @GetMapping("/api/qr")
    public Map<String, String> getZatcaQrCode() throws Exception {
        String sellerName = "Company X";
        String vatNumber = "300385812700003";
        String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String totalWithVat = "1000.00";
        String vatAmount = "150.00";

        String zatcaBase64 = TLVUtil.generateZatcaBase64(sellerName, vatNumber, timestamp, totalWithVat, vatAmount);
        String qrBase64 = QRCodeGenerator.generateQRCodeImage(zatcaBase64, 300, 300);

        Map<String, String> response = new HashMap<>();
        response.put("qrCodeBase64", "data:image/png;base64," + qrBase64);
        return response;
    }
}
