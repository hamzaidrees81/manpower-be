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
public class PaymentSummaryDTO {
    private Integer paymentId;
    private LocalDate paymentDate;
    private Float amount;
    private String status; // Complete, Pending, Failed
}
