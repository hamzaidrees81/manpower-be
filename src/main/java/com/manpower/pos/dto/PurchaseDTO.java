package com.manpower.pos.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    private Integer supplierId;
    private Integer shopId;
    private String supplierInvoiceNo;
    private List<PurchaseItemDTO> items;

    //additional params
    private SupplierDTO supplier;
    private ShopDTO shop;
    private Instant purchaseDate;
}