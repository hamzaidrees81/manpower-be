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
    private final StockMovementMapper stockMovementMapper;

    public PurchaseMapper(ShopMapper shopMapper, SupplierMapper supplierMapper, StockMovementMapper stockMovementMapper) {
        this.shopMapper = shopMapper;
        this.supplierMapper = supplierMapper;
        this.stockMovementMapper = stockMovementMapper;
    }

    public PurchaseDTO toDTO(Purchase purchase, List<StockMovement> purchaseItems) {
        PurchaseDTO purchaseDTO = toDTO(purchase);
        purchaseDTO.setItems(purchaseItems.stream().map(stockMovementMapper::toPurchaseDto).toList());
        return purchaseDTO;
    }

    public PurchaseDTO toDTO(Purchase purchase) {
        return PurchaseDTO
                .builder()
                .purchaseId(purchase.getId())
                .shop(shopMapper.toDTO(purchase.getShop()))
                .supplier(supplierMapper.toDTO(purchase.getSupplier()))
                .supplierInvoiceNo(purchase.getSupplierInvoiceNumber())
                .purchaseDate(purchase.getDate())
                .totalAmount(purchase.getTotalAmount())
                .totalVATAmount(purchase.getVatAmount())
                .paidAmount(purchase.getPaidAmount())
                .totalBeforeVat(purchase.getTotalBeforeVat())
                .build();
    }
}
