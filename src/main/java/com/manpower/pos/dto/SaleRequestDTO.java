package com.manpower.pos.dto;

import com.manpower.model.Payment;
import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.model.Shop;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleRequestDTO {
    private BigDecimal totalAmount;
    private AliveStatus status;
    private Integer customerId;
    private LocalDateTime saleDate;
    private Integer shopId;
    private String poNumber;
//    private List<Payment> payments;
    private BigDecimal paidAmount;
    private List<SaleItemDTO> saleItems;
}
