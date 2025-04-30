package com.manpower.pos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequestDTO {
    private BigDecimal totalAmount;
    private String status;
    private Integer customerId;
    private List<SaleItemDTO> saleItems;
}
