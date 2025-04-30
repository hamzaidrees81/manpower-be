package com.manpower.pos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class SaleResponseDTO {
    private Integer id;
    private Instant saleDate;
    private BigDecimal totalAmount;
    private String status;
    private Integer customerId;
    private List<SaleItemResponseDTO> saleItems;
}
