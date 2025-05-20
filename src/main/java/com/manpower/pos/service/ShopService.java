package com.manpower.pos.service;

import com.manpower.model.Company;
import com.manpower.pos.dto.ShopDTO;
import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.mapper.ShopMapper;
import com.manpower.pos.model.Shop;
import com.manpower.pos.repository.ShopRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    public void createShop(ShopDTO dto) {
        Shop shop = ShopMapper.toEntity(dto);
        shop.setCompany(Company.builder().id(SecurityUtil.getCompanyClaim()).build()); // inject company
        shop.setStatus(AliveStatus.ACTIVE);
        shopRepository.save(shop);
    }

    public List<ShopDTO> getAllShops() {
        return shopRepository.findAll().stream()
                .filter(s -> s.getCompany().getId().equals(com.manpower.util.SecurityUtil.getCompanyClaim()))
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ShopDTO getShopById(Integer id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        return shopMapper.toDTO(shop);
    }
}
