package com.manpower.pos.mapper;

import com.manpower.model.Company;
import com.manpower.pos.dto.ShopDTO;
import com.manpower.pos.model.Shop;
import com.manpower.util.SecurityUtil;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {

    public static Shop toEntity(ShopDTO dto) {
        if (dto == null) return null;

        Shop shop = new Shop();
        shop.setId(dto.getId());
        shop.setShopName(dto.getShopName());
        shop.setShopAddress(dto.getShopAddress());
        shop.setShopPhone1(dto.getShopPhone1());
        shop.setShopPhone2(dto.getShopPhone2());
        shop.setComments(dto.getComments());
        shop.setStatus(dto.getStatus());
        shop.setDateCreated(dto.getDateCreated());
        return shop;
    }

    public ShopDTO toDTO(Shop shop) {
        if (shop == null) return null;

        ShopDTO dto = new ShopDTO();
        dto.setId(shop.getId());
        dto.setShopName(shop.getShopName());
        dto.setShopAddress(shop.getShopAddress());
        dto.setShopPhone1(shop.getShopPhone1());
        dto.setShopPhone2(shop.getShopPhone2());
        dto.setComments(shop.getComments());
        dto.setStatus(shop.getStatus());
        dto.setDateCreated(shop.getDateCreated());

        if (shop.getCompany() != null) {
            dto.setCompanyId(shop.getCompany().getId());
        }

        return dto;
    }
}
