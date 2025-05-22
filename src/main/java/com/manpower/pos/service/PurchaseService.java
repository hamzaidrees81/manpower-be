package com.manpower.pos.service;

import com.manpower.model.Company;
import com.manpower.pos.dto.PurchaseDTO;
import com.manpower.pos.dto.PurchaseFilterDTO;
import com.manpower.pos.dto.PurchaseItemDTO;
import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.enums.RelatedEntityType;
import com.manpower.pos.enums.StockMovementReason;
import com.manpower.pos.enums.StockMovementType;
import com.manpower.pos.filter.PurchaseSpecification;
import com.manpower.pos.mapper.PurchaseMapper;
import com.manpower.pos.model.*;
import com.manpower.pos.repository.PurchaseRepository;
import com.manpower.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StockService stockService;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    public List<PurchaseDTO> filterPurchases(PurchaseFilterDTO dto) {
        List<Purchase> purchases = purchaseRepository.findAll(PurchaseSpecification.filter(dto));
        return purchases.stream().map(purchaseMapper::toDTO).toList();
    }

    @Transactional
    public Integer addPurchase(PurchaseDTO purchaseDTO) {

        Integer shopId = purchaseDTO.getShopId();

        //create purchase
        Purchase purchase = Purchase.builder()
                .date(Instant.now())
                .supplierInvoiceNumber(purchaseDTO.getSupplierInvoiceNo())
                .company(Company.builder().id(SecurityUtil.getCompanyClaim()).build())
                .totalAmount(BigDecimal.ZERO)
                .supplier(Supplier.builder().id(purchaseDTO.getSupplierId()).build())
                .shop(Shop.builder().id(shopId).build())
                .status(AliveStatus.ACTIVE)
                .paidAmount(purchaseDTO.getPaidAmount() != null ? purchaseDTO.getPaidAmount() : BigDecimal.ZERO)
                .vatAmount(purchaseDTO.getTotalVATAmount()!=null ? purchaseDTO.getTotalVATAmount() : BigDecimal.ZERO)
                .build();

        purchase = purchaseRepository.save(purchase);

        Integer purchaseId = purchase.getId();

        //calculate total purchase amount
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(PurchaseItemDTO item : purchaseDTO.getItems()) {

            //find stock of this item
            Optional<Stock> existingStockOtp = stockService.getStockByProductIdAndShopId(item.getProductId(), shopId);

            Stock stock = null;
            //prepare a new entry for stock at this shop or return existing
            stock = existingStockOtp.orElseGet(() -> Stock.builder()
                    .shop(Shop.builder().id(purchaseDTO.getShopId()).build())
                    .product(Product.builder().id(item.getProductId()).build())
                    .company(Company.builder().id(SecurityUtil.getCompanyClaim()).build())
                    .quantity(BigDecimal.ZERO) //initialize with zero
                    .retailPrice(item.getRetailPrice())
                    .minSalePrice(item.getBuyPrice())
                    .status(AliveStatus.ACTIVE)
                    .build());

            //sum old + new quantity
            stock.setQuantity(stock.getQuantity().add(item.getQuantity()));

            //based on pricing strategy, set current price or set maximum price
            switch (item.getPricingStrategy()) {
                case LATEST_PRICE -> {
                    stock.setRetailPrice(item.getRetailPrice());
                    stock.setMinSalePrice(item.getMinSalePrice());
                }
                case MAXIMUM_PRICE -> {
                    //set which ever is greater, current or new
                    stock.setRetailPrice(
                            item.getRetailPrice().compareTo(stock.getRetailPrice()) >= 0 //means retail is bigger
                                    ? item.getRetailPrice()
                                    : stock.getRetailPrice()
                    );
                    stock.setMinSalePrice(
                            item.getMinSalePrice().compareTo(stock.getMinSalePrice()) >= 0 //means retail is bigger
                                    ? item.getMinSalePrice()
                                    : stock.getMinSalePrice()
                    );
                }
                case MIN_PRICE -> {
                    stock.setRetailPrice(
                            item.getRetailPrice().compareTo(stock.getRetailPrice()) <= 0 //means retail is less
                                    ? item.getRetailPrice()
                                    : stock.getRetailPrice()
                    );
                    stock.setMinSalePrice(
                            item.getMinSalePrice().compareTo(stock.getMinSalePrice()) <= 0 //means retail is bigger
                                    ? item.getMinSalePrice()
                                    : stock.getMinSalePrice()
                    );
                }
                default -> throw new IllegalStateException("Unexpected value: ");
            }

            stockService.createStock(stock);

            //create a stock movement
            StockMovement stockMovement = StockMovement
                    .builder()
                    .product(stock.getProduct())
                    .quantity(item.getQuantity())
                    .remQty(item.getQuantity()) //assign rem qty
                    .buyPrice(item.getBuyPrice())
                    .retailPrice(item.getRetailPrice())
                    .minPrice(item.getMinSalePrice())
                    .movementType(StockMovementType.IN)
                    .movementReason(StockMovementReason.PURCHASE)
                    .movementDate(Instant.now())
                    .relatedEntityId(purchaseId)
                    .relatedEntityType(RelatedEntityType.PURCHASE)
                    .batch(item.getBatchNo())
                    .comments(item.getComments())
                    .storageRack(item.getStorageRack())
                    .company(stock.getCompany())
                    .vatAmount(item.getVatAmount())
                    .status(AliveStatus.ACTIVE)
                    .shop(stock.getShop())
                    .build();

            stockService.createStockMovement(stockMovement);

            totalAmount = totalAmount.add(stockMovement.getBuyPrice().multiply(stockMovement.getQuantity()));
        }

        //assign total amount back
        purchase.setTotalAmount(totalAmount);
        purchaseRepository.save(purchase);
        return purchaseId;
    }

    public PurchaseDTO getPurchase(Integer purchaseId) {

        //get purchase row
        Optional<Purchase> purchaseOtp = purchaseRepository.findById(purchaseId);
        if(purchaseOtp.isEmpty()) {
            throw new RuntimeException("Purchase not found");
        }

        Purchase purchase = purchaseOtp.get();

        //get all purchase items
        List<StockMovement> purchaseItems = stockService.findPurchaseItems(RelatedEntityType.PURCHASE, purchaseId);

        return purchaseMapper.toDTO(purchase, purchaseItems);
    }
}
