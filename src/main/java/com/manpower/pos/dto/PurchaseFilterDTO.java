package com.manpower.pos.dto;

import com.manpower.pos.enums.AliveStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Data
public class PurchaseFilterDTO {
    private Integer purchaseId;
    private Integer supplierId;
    private Integer shopId;
    private String supplierInvoiceNumber;
    private AliveStatus status; // Add this line
    private Instant dateFrom;     // Start of date range
    private Instant dateTo;
}
