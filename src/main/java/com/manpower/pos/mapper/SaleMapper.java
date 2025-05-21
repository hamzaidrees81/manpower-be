package com.manpower.pos.mapper;

import com.manpower.model.Company;
import com.manpower.model.User;
import com.manpower.pos.dto.PurchaseDTO;
import com.manpower.pos.dto.SaleItemResponseDTO;
import com.manpower.pos.dto.SaleRequestDTO;
import com.manpower.pos.dto.SaleResponseDTO;
import com.manpower.pos.model.Purchase;
import com.manpower.pos.model.Sale;
import com.manpower.pos.model.SaleItem;
import com.manpower.pos.model.StockMovement;
import com.manpower.pos.repository.ProductRepository;
import com.manpower.pos.repository.ShopRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SaleMapper {
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final StockMovementMapper stockMovementMapper;

    public Sale toEntity(SaleRequestDTO dto, Company company, User user) {
        Sale sale = new Sale();
        sale.setDate(Instant.now());
        sale.setStatus(dto.getStatus());
        sale.setTotalAmount(dto.getTotalAmount());
        sale.setCompany(Company.builder().id(SecurityUtil.getCompanyClaim()).build());
        sale.setClientId(dto.getCustomerId());
        sale.setShop(shopRepository.findById(dto.getShopId()).get());
        sale.setPoNumber(dto.getPoNumber());
        sale.setPaidAmount(dto.getPaidAmount() !=null ? dto.getPaidAmount() : BigDecimal.ZERO);
        sale.setVatAmount(dto.getVatAmount() !=null ? dto.getVatAmount() : BigDecimal.ZERO);
        return sale;
    }

    public SaleResponseDTO toDTO(Sale sale, List<StockMovement> saleItems) {
        SaleResponseDTO saleResponseDTO = toResponseDTO(sale);
        saleResponseDTO.setSaleItems(saleItems.stream().map(stockMovementMapper::toSaleDTO).toList());
        return saleResponseDTO;
    }

    // Mapping from entity to response DTO
    public SaleResponseDTO toResponseDTO(Sale sale) {
        SaleResponseDTO responseDTO = new SaleResponseDTO();
        responseDTO.setId(sale.getId());
        responseDTO.setSaleDate(sale.getDate());
        responseDTO.setTotalAmount(sale.getTotalAmount());
        responseDTO.setStatus(sale.getStatus());
        responseDTO.setCustomerId(sale.getClientId());
        responseDTO.setShopId(sale.getShop().getId());
        responseDTO.setShop(sale.getShop());
        responseDTO.setPoNumber(sale.getPoNumber());
        return responseDTO;
    }
}
