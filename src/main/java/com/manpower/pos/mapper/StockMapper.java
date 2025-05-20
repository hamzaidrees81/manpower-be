package com.manpower.pos.mapper;

import com.manpower.pos.dto.StockDto;
import com.manpower.pos.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    private final ProductMapper productMapper;
    private final SupplierMapper supplierMapper;
    private final ShopMapper shopMapper;

    public StockMapper(ProductMapper productMapper, SupplierMapper supplierMapper, ShopMapper shopMapper) {
        this.productMapper = productMapper;
        this.supplierMapper = supplierMapper;
        this.shopMapper = shopMapper;
    }

    public StockDto toDto(Stock stock) {
        if (stock == null) {
            return null;
        }

        StockDto dto = new StockDto();
        dto.setId(stock.getId());
        dto.setProductId(stock.getProduct().getId());
        dto.setProduct(productMapper.toDto(stock.getProduct()));
        dto.setQuantity(stock.getQuantity());
        dto.setRetailPrice(stock.getRetailPrice());
        dto.setMinPrice(stock.getMinSalePrice());
        dto.setShop(shopMapper.toDTO(stock.getShop()));
        dto.setStorageRack(stock.getStorageRack());
        return dto;
    }

    public Stock toEntity(StockDto stockDto) {
        return null;
    }

//    public Stock toEntity(StockDto dto) {
//        if (dto == null) {
//            return null;
//        }
//
//        Stock stock = new Stock();
//        stock.setId(dto.getId());
//        stock.setQuantity(dto.getQuantity());
//        stock.setRetailPrice(dto.getPrice());
//        // Assuming the product, supplier, and company are fetched or set later
//        return stock;
//    }
}
