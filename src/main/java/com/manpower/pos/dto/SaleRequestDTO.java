package com.manpower.pos.dto;

import com.manpower.model.Payment;
import com.manpower.pos.model.Shop;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleRequestDTO {
    private BigDecimal totalAmount;
    private String status;
    private Integer customerId;
    private LocalDateTime saleDate;
    private Shop shop;
    private Integer shopId;
    private String poNumber;
    private List<Payment> payments;

    private List<SaleItemDTO> saleItems;
}
