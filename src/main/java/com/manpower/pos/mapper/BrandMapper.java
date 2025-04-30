package com.manpower.pos.mapper;

import com.manpower.model.Company;
import com.manpower.pos.dto.BrandDto;
import com.manpower.pos.model.Brand;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandMapper {

    private final com.manpower.repository.CompanyRepository companyRepository;

    public BrandDto toDto(Brand brand) {
        BrandDto dto = new BrandDto();
        dto.setId(brand.getId());
        dto.setBrandName(brand.getBrandName());
        return dto;
    }

    public Brand toEntity(BrandDto dto) {
        Company company = companyRepository.findById(SecurityUtil.getCompanyClaim())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
        return Brand.builder()
                .id(dto.getId())
                .brandName(dto.getBrandName())
                .company(company)
                .build();
    }
}
