package com.manpower.model.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSummaryDTO {
    private Integer invoiceId;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private Float amount;
    private String status; // Paid, Unpaid, Overdue, Not Due
}
