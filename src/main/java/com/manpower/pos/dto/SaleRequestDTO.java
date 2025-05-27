package com.manpower.pos.dto;

import com.manpower.pos.enums.AliveStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleRequestDTO {
    private BigDecimal totalBeforeVat;
    private BigDecimal discountPercentage;
    private BigDecimal totalAmount;
    private BigDecimal vatAmount;
    private AliveStatus status;
    private Integer customerId;
    private LocalDateTime saleDate;
    private Integer shopId;
    private String poNumber;
    private BigDecimal paidAmount;
    private List<SaleItemDTO> saleItems;
}
