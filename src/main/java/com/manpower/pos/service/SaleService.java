package com.manpower.pos.service;

import com.manpower.model.Company;
import com.manpower.model.User;
import com.manpower.pos.dto.PurchaseDTO;
import com.manpower.pos.dto.SaleItemDTO;
import com.manpower.pos.dto.SaleRequestDTO;
import com.manpower.pos.dto.SaleResponseDTO;
import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.enums.RelatedEntityType;
import com.manpower.pos.enums.StockMovementReason;
import com.manpower.pos.enums.StockMovementType;
import com.manpower.pos.mapper.SaleMapper;
import com.manpower.pos.model.*;
import com.manpower.pos.repository.SaleRepository;
import com.manpower.pos.repository.StockMovementRepository;
import com.manpower.repository.CompanyRepository;
import com.manpower.repository.UserRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final CompanyRepository companyRepository;
    private final SaleMapper saleMapper;
    private final UserRepository userRepository;
    private final StockService stockService;
    private final StockMovementRepository stockMovementRepository;

    @Transactional
    public SaleResponseDTO createSale(SaleRequestDTO dto) {
        Integer companyId = SecurityUtil.getCompanyClaim();
        Integer userId = SecurityUtil.getUserClaim();

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Sale saleInput = saleMapper.toEntity(dto, company, user);
        saleInput.setStatus(AliveStatus.ACTIVE);
        final Sale sale = saleRepository.save(saleInput);

        //store sale changes in stock movement

        Integer saleId = sale.getId();
        Integer shopId = dto.getShopId();

        //calculate total purchase amount
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleItemDTO item : dto.getSaleItems()) {

            //find stock of this item
            Optional<Stock> existingStockOtp = stockService.getStockByProductIdAndShopId(item.getProductId(), shopId);

            Stock stock = null;
            //if this item is not ever in stocks table, possible?
            //ITEM MUST BE IN STOCK
            if (existingStockOtp.isEmpty()) {
                throw new RuntimeException("Stock never entered for the item with id " + item.getProductId());
            }
            stock = existingStockOtp.get();

            //now select item to subtract from this stock, get a list from stock movement
            //1. check if enough quantity is available to sell
            if (item.getQuantity().compareTo(stock.getQuantity()) > 0) {
                throw new RuntimeException("Stock not available for this item " + stock.getProduct().getName());
            }

            //sum old + new quantity
            stock.setQuantity(stock.getQuantity().subtract(item.getQuantity()));
            stockService.updateStock(stock);

            //record stock movement
            BigDecimal quantityToSell = item.getQuantity();

            // get the list oldest to earliest
            List<StockMovement> stockMovements = stockService.findAvailableStockMovementListAsc(shopId, item.getProductId());

            for (StockMovement stockMovement : stockMovements) {
                if (quantityToSell.compareTo(BigDecimal.ZERO) <= 0) break;

                BigDecimal remQty = stockMovement.getRemQty();

                if (remQty.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal qtyToDeduct = remQty.min(quantityToSell);

                    // Deduct from existing stock
                    stockMovement.setRemQty(remQty.subtract(qtyToDeduct));
                    stockMovementRepository.save(stockMovement);

                    // ✅ Create OUT stock movement referencing this deduction
                    StockMovement outMovement = StockMovement
                            .builder()
                            .product(stockMovement.getProduct())
                            .quantity(qtyToDeduct)
                            .buyPrice(stockMovement.getBuyPrice())
                            .retailPrice(item.getUnitPrice()) // or use stockMovement.getRetailPrice() if needed
                            .soldPrice(item.getSoldPrice())
                            .movementType(StockMovementType.OUT)
                            .movementReason(StockMovementReason.SALE) // or whatever your reason is
                            .movementDate(Instant.now())
                            .relatedEntityId(saleId) // replace with the ID of your current transaction
                            .relatedEntityType(RelatedEntityType.SALE) // update accordingly
                            .batch(stockMovement.getBatch())
                            .comments("Sold from stock movement ID: " + stockMovement.getId())
                            .storageRack(stockMovement.getStorageRack())
                            .company(stockMovement.getCompany())
                            .vatAmount(item.getTax()) // or recalculate proportionally
                            .status(AliveStatus.ACTIVE)
                            .shop(stockMovement.getShop())
                            .build();

                    stockMovementRepository.save(outMovement);

                    // Decrease the amount left to sell
                    quantityToSell = quantityToSell.subtract(qtyToDeduct);
                }
            }
        }

        //query the logs

        return saleMapper.toResponseDTO(sale);
    }


    public SaleResponseDTO getSale(Integer saleId) {

        //get purchase row
        Optional<Sale> saleOpt = saleRepository.findById(saleId);
        if(saleOpt.isEmpty()) {
            throw new RuntimeException("Purchase not found");
        }

        Sale sale = saleOpt.get();

        //get all purchase items
        List<StockMovement> saleItems = stockService.findSaleItems(RelatedEntityType.SALE, saleId);
        return saleMapper.toDTO(sale, saleItems);
    }
}
