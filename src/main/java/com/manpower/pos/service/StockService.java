package com.manpower.pos.service;

import com.manpower.pos.dto.StockDto;
import com.manpower.pos.enums.RelatedEntityType;
import com.manpower.pos.enums.StocksForPage;
import com.manpower.pos.mapper.StockMapper;
import com.manpower.pos.mapper.StockMovementMapper;
import com.manpower.pos.model.Stock;
import com.manpower.pos.model.StockMovement;
import com.manpower.pos.repository.StockRepository;
import com.manpower.pos.repository.StockMovementRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockMovementRepository stockMovementRepository;
    private final StockMapper stockMapper;
    private final StockMovementMapper stockMovementMapper;

    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(stockMapper::toDto)
                .toList();
    }

    public Optional<Stock> getStockByProductIdAndShopId(Integer productId, Integer shopId) {
        return stockRepository.findByProduct_IdAndShop_Id(productId, shopId);
    }

    public Optional<StockDto> getStockById(Integer id) {
        return stockRepository.findById(id)
                .map(stockMapper::toDto);
    }

    public StockDto createStock(StockDto stockDto) {
        Stock stock = stockMapper.toEntity(stockDto);
        Stock saved = stockRepository.save(stock);
        return stockMapper.toDto(saved);
    }

    public void createStock(Stock stock) {
        stockRepository.save(stock);
    }

    public Optional<StockDto> updateStock(Integer id, StockDto stockDto) {
        return stockRepository.findById(id)
                .map(existingStock -> {
                    Stock updatedStock = stockMapper.toEntity(stockDto);
                    updatedStock.setId(id);
                    Stock saved = stockRepository.save(updatedStock);
                    return stockMapper.toDto(saved);
                });
    }

    public Stock updateStock(Stock stock) {
        return stockRepository.save(stock);
    }


    public boolean deleteStock(Integer id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Stock Movement Methods

//    public List<StockMovementDto> getAllStockMovements() {
//        List<StockMovement> stockMovements = stockMovementRepository.findAll();
//        return stockMovements.stream()
//                .map(stockMovementMapper::toDto)
//                .toList();
//    }

//    public List<StockMovementDto> getAllStockMovementsByPurchaseId(Integer purchaseId) {
//        List<StockMovement> stockMovements = stockMovementRepository.findAll();
//        return stockMovements.stream()
//                .map(stockMovementMapper::toDto)
//                .toList();
//    }

//    public StockMovementDto createStockMovement(StockMovementDto stockMovementDto) {
//        StockMovement stockMovement = stockMovementMapper.toEntity(stockMovementDto);
//        StockMovement savedMovement = stockMovementRepository.save(stockMovement);
//
//        // Update stock quantity based on the movement
//        updateStockQuantity(stockMovement);
//
//        return stockMovementMapper.toDto(savedMovement);
//    }

    public void createStockMovement(StockMovement stockMovement) {
        stockMovementRepository.save(stockMovement);
    }

    private void updateStockQuantity(StockMovement stockMovement) {
        Optional<Stock> stockOptional = stockRepository.findByProduct_Id(stockMovement.getProduct().getId());
        stockOptional.ifPresent(stock -> {
            if ("IN".equals(stockMovement.getMovementType())) {
                stock.setQuantity(stock.getQuantity().add(stockMovement.getQuantity()));
            } else if ("OUT".equals(stockMovement.getMovementType())) {
                stock.setQuantity(stock.getQuantity().subtract(stockMovement.getQuantity()));
            }
            stockRepository.save(stock);
        });
    }

    public List<StockMovement> findPurchaseItems(RelatedEntityType relatedEntityType, Integer purchaseId) {
        return stockMovementRepository.findByRelatedEntityTypeAndRelatedEntityId(relatedEntityType, purchaseId);
    }

    public List<StockMovement> findSaleItems(RelatedEntityType relatedEntityType, Integer saleId) {
        return stockMovementRepository.findByRelatedEntityTypeAndRelatedEntityId(relatedEntityType, saleId);
    }

    public List<Stock> getAllStocksByCompanyId() {
        return stockRepository.findAllByCompanyId(SecurityUtil.getCompanyClaim());

    }

    public List<Stock> getAllStocksByProductIdShopIdCompanyId(Integer productId, Integer shopId) {
        return stockRepository.findAllByProduct_IdAndShop_IdAndCompany_Id(productId, shopId, SecurityUtil.getCompanyClaim());
    }

    public List<Stock> getAllStocksByShopIdCompanyId(Integer shopId) {
        return stockRepository.findAllByShop_IdAndCompany_Id(shopId, SecurityUtil.getCompanyClaim());
    }

    public List<Stock> getProductStocksAllShopsCompanyId(Integer productId) {
        return stockRepository.findAllByProduct_IdAndCompany_Id(productId, SecurityUtil.getCompanyClaim());
    }


    public List<StockMovement> findAvailableStockMovementListAsc(Integer shopId, Integer productId) {
        return stockMovementRepository.
                findAllByProduct_IdAndShop_IdAndCompany_IdAndRemQtyGreaterThanOrderByIdAsc(
                        productId,
                        shopId,
                        SecurityUtil.getCompanyClaim(),
                        BigDecimal.ZERO
                );
    }

    public List<StockDto> findStocks(Integer shopId, Integer productId, StocksForPage stocksForPage) {
        List<StockDto> stocksList;

        //get all stocks or by shop or by product id
        if (shopId != null && productId != null) {
            stocksList = getAllStocksByProductIdShopIdCompanyId(productId, shopId).stream().map(stockMapper::toDto).toList();
        }
        if (shopId != null && productId == null) {
            stocksList = getAllStocksByShopIdCompanyId(shopId).stream().map(stockMapper::toDto).toList();
        } else if (shopId == null && productId != null) {
            stocksList = getProductStocksAllShopsCompanyId(productId).stream().map(stockMapper::toDto).toList();
        } else
            stocksList = getAllStocksByCompanyId().stream().map(stockMapper::toDto).toList();

        if (StocksForPage.PURCHASE.equals(stocksForPage)) {
            //also show buy price if purchase - take buy from latest stock movement
            for (StockDto stockDto : stocksList) {
                Optional<StockMovement> stockMovementOtp =
                        stockMovementRepository.
                                findTopByProduct_IdAndShop_IdAndCompany_IdOrderByIdDesc(
                                        stockDto.getProductId(),
                                        shopId,
                                        SecurityUtil.getCompanyClaim()
                                );
                if (stockMovementOtp.isEmpty())
                    continue;
                StockMovement stockMovement = stockMovementOtp.get();
                stockDto.setBuyPrice(stockMovement.getBuyPrice());
            }

        }

        return stocksList;
    }
}
