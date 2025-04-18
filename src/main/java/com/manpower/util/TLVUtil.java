package com.manpower.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TLVUtil {
    public static byte[] getTLVBytes(int tag, String value) {
        byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] tlv = new byte[2 + valueBytes.length];
        tlv[0] = (byte) tag;
        tlv[1] = (byte) valueBytes.length;
        System.arraycopy(valueBytes, 0, tlv, 2, valueBytes.length);
        return tlv;
    }

    public static String generateZatcaBase64(String sellerName, String vatRegNumber, String timestamp, String total, String vatTotal) {
        byte[] tlv1 = getTLVBytes(1, sellerName);
        byte[] tlv2 = getTLVBytes(2, vatRegNumber);
        byte[] tlv3 = getTLVBytes(3, timestamp);
        byte[] tlv4 = getTLVBytes(4, total);
        byte[] tlv5 = getTLVBytes(5, vatTotal);

        byte[] all = new byte[tlv1.length + tlv2.length + tlv3.length + tlv4.length + tlv5.length];
        System.arraycopy(tlv1, 0, all, 0, tlv1.length);
        System.arraycopy(tlv2, 0, all, tlv1.length, tlv2.length);
        System.arraycopy(tlv3, 0, all, tlv1.length + tlv2.length, tlv3.length);
        System.arraycopy(tlv4, 0, all, tlv1.length + tlv2.length + tlv3.length, tlv4.length);
        System.arraycopy(tlv5, 0, all, tlv1.length + tlv2.length + tlv3.length + tlv4.length, tlv5.length);

        return Base64.getEncoder().encodeToString(all);
    }
}
