package com.manpower.pos.mapper;

import com.manpower.pos.dto.PurchaseDTO;
import com.manpower.pos.model.Purchase;
import com.manpower.pos.model.StockMovement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseMapper {
    private final ShopMapper shopMapper;
    private final SupplierMapper supplierMapper;

    public PurchaseMapper(ShopMapper shopMapper, SupplierMapper supplierMapper) {
        this.shopMapper = shopMapper;
        this.supplierMapper = supplierMapper;
    }

    public PurchaseDTO toDTO(Purchase purchase, List<StockMovement> purchaseItems) {
        PurchaseDTO purchaseDTO = toDTO(purchase);
        purchaseDTO.setItems(purchaseItems.stream().map(StockMovementMapper::toDto).toList());
        return purchaseDTO;
    }

    public PurchaseDTO toDTO(Purchase purchase) {
        return PurchaseDTO
                .builder()
                .shop(shopMapper.toDTO(purchase.getShop()))
                .supplier(supplierMapper.toDTO(purchase.getSupplier()))
                .supplierInvoiceNo(purchase.getSupplierInvoiceNumber())
                .purchaseDate(purchase.getDate())
                .totalAmount(purchase.getTotalAmount())
                .totalVATAmount(purchase.getVatAmount())
                .paidAmount(purchase.getPaidAmount())
                .build();
    }
}
