package com.manpower.pos.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    private Integer purchaseId;
    private Integer supplierId;
    private Integer shopId;
    private String supplierInvoiceNo;
    private List<PurchaseItemDTO> items;
    private BigDecimal paidAmount;

    private BigDecimal totalAmount;
    private BigDecimal totalVATAmount;

    //additional params
    private SupplierDTO supplier;
    private ShopDTO shop;
    private Instant purchaseDate;
}