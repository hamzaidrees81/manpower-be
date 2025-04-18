package com.manpower.service;

import com.manpower.util.QRCodeGenerator;
import com.manpower.util.TLVUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZatkaService {

    String generateQR(String sellerName , String vatNumber, String timestamp, String totalWithVat, String vatAmount) throws Exception {

        String zatcaBase64 = TLVUtil.generateZatcaBase64(sellerName, vatNumber, timestamp, totalWithVat, vatAmount);
        String qrBase64 = QRCodeGenerator.generateQRCodeImage(zatcaBase64, 300, 300);

        return "data:image/png;base64," + qrBase64;
    }
}
