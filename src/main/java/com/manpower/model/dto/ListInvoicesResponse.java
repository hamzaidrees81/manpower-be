package com.manpower.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Data
public class ListInvoicesResponse {
    private Page<InvoiceStatusDTO> page;
    private BigDecimal totalAmount;
    private BigDecimal pendingAmount;
    private BigDecimal paidAmount;

    public ListInvoicesResponse(Page<InvoiceStatusDTO> page, BigDecimal totalAmount, BigDecimal pendingAmount, BigDecimal paidAmount) {
        this.page = page;
        this.totalAmount = totalAmount;
        this.pendingAmount = pendingAmount;
        this.paidAmount = paidAmount;
    }
}
