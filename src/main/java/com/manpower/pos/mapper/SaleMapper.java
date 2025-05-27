package com.manpower.pos.mapper;

import com.manpower.mapper.ClientMapper;
import com.manpower.model.Client;
import com.manpower.model.Company;
import com.manpower.model.User;
import com.manpower.pos.dto.SaleRequestDTO;
import com.manpower.pos.dto.SaleResponseDTO;
import com.manpower.pos.model.Sale;
import com.manpower.pos.model.StockMovement;
import com.manpower.pos.repository.ProductRepository;
import com.manpower.pos.repository.ShopRepository;
import com.manpower.service.ZatkaService;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaleMapper {
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final StockMovementMapper stockMovementMapper;
    private final ShopMapper shopMapper;
    private final ZatkaService zatkaService;

    public Sale toEntity(SaleRequestDTO dto, Company company, User user) {
        Sale sale = new Sale();
        sale.setDate(dto.getSaleDate());
        sale.setStatus(dto.getStatus());
        sale.setTotalAmount(dto.getTotalAmount());
        sale.setCompany(Company.builder().id(SecurityUtil.getCompanyClaim()).build());
        sale.setClient(Client.builder().id(dto.getCustomerId()).build());
        sale.setShop(shopRepository.findById(dto.getShopId()).get());
        sale.setPoNumber(dto.getPoNumber());
        sale.setPaidAmount(dto.getPaidAmount() !=null ? dto.getPaidAmount() : BigDecimal.ZERO);
        sale.setVatAmount(dto.getVatAmount() !=null ? dto.getVatAmount() : BigDecimal.ZERO);
        sale.setTotalBeforeVat(dto.getTotalBeforeVat());
        sale.setDiscountPercentage(dto.getDiscountPercentage());
        return sale;
    }

    public SaleResponseDTO toDTO(Sale sale, List<StockMovement> saleItems) throws Exception {
        SaleResponseDTO saleResponseDTO = toResponseDTO(sale);
//        public String generateQR(String sellerName , String vatNumber, String timestamp, String totalWithVat, String vatAmount) throws Exception {

        String qrCode = zatkaService.generateQR(sale.getCompany().getName(), sale.getCompany().getVat(),
                sale.getDate().toString(), sale.getTotalAmount().toString(), sale.getVatAmount().toString());
        saleResponseDTO.setQRCode(qrCode);
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
        responseDTO.setCustomerId(sale.getClient().getId());
        responseDTO.setClient(ClientMapper.toDTO(sale.getClient()));
        responseDTO.setShopId(sale.getShop().getId());
        responseDTO.setShop(shopMapper.toDTO(sale.getShop()));
        responseDTO.setPoNumber(sale.getPoNumber());
        sale.setTotalBeforeVat(sale.getTotalBeforeVat());
        sale.setDiscountPercentage(sale.getDiscountPercentage());

        return responseDTO;
    }
}
