package com.manpower.pos.mapper;

import com.manpower.model.Company;
import com.manpower.model.User;
import com.manpower.pos.dto.SaleItemResponseDTO;
import com.manpower.pos.dto.SaleRequestDTO;
import com.manpower.pos.dto.SaleResponseDTO;
import com.manpower.pos.model.Sale;
import com.manpower.pos.model.SaleItem;
import com.manpower.pos.repository.ProductRepository;
import com.manpower.pos.repository.ShopRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SaleMapper {
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    public Sale toEntity(SaleRequestDTO dto, Company company, User user) {
        Sale sale = new Sale();
        sale.setDate(Instant.now());
        sale.setStatus(dto.getStatus());
        sale.setTotalAmount(dto.getTotalAmount());
        sale.setCompany(Company.builder().id(SecurityUtil.getCompanyClaim()).build());
        sale.setCustomerId(dto.getCustomerId());
        sale.setShop(shopRepository.findById(Long.valueOf(dto.getShopId())).get());
        sale.setPoNumber(dto.getPoNumber());

        List<SaleItem> items = dto.getSaleItems().stream().map(itemDTO -> {
            SaleItem item = new SaleItem();
            item.setProduct(productRepository.getReferenceById(Long.valueOf(itemDTO.getProductId())));
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            item.setDiscount(itemDTO.getDiscount());
            item.setTax(itemDTO.getTax());
            item.setSale(sale);
            item.setCompany(company);
            return item;
        }).collect(Collectors.toList());

        sale.setSaleItems(items);
        return sale;
    }

    // Mapping from entity to response DTO
    public SaleResponseDTO toResponseDTO(Sale sale) {
        SaleResponseDTO responseDTO = new SaleResponseDTO();
        responseDTO.setId(sale.getId());
        responseDTO.setSaleDate(sale.getDate());
        responseDTO.setTotalAmount(sale.getTotalAmount());
        responseDTO.setStatus(sale.getStatus());
        responseDTO.setCustomerId(sale.getCustomerId());
        responseDTO.setShopId(sale.getShop().getId());
        responseDTO.setShop(sale.getShop());
        responseDTO.setPoNumber(sale.getPoNumber());

        List<SaleItemResponseDTO> itemDTOs = sale.getSaleItems().stream().map(item -> {
            SaleItemResponseDTO itemDTO = new SaleItemResponseDTO();
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUnitPrice(item.getUnitPrice());
            itemDTO.setDiscount(item.getDiscount());
            itemDTO.setTax(item.getTax());
            itemDTO.setTotalPrice(
                    item.getUnitPrice().multiply(item.getQuantity())
                            .subtract(item.getDiscount()).add(item.getTax())
            );
            return itemDTO;
        }).collect(Collectors.toList());

        //TODO: HANDLE PAYMENTS LIKE SALE ITEMS

        responseDTO.setSaleItems(itemDTOs);
        return responseDTO;
    }
}
