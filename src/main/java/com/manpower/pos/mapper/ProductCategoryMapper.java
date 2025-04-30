package com.manpower.pos.mapper;

import com.manpower.model.Company;
import com.manpower.pos.dto.ProductCategoryDto;
import com.manpower.pos.model.ProductCategory;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCategoryMapper {

    private final com.manpower.repository.CompanyRepository companyRepository;

    public ProductCategoryDto toDto(ProductCategory category) {
        ProductCategoryDto dto = new ProductCategoryDto();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }

    public ProductCategory toEntity(ProductCategoryDto dto) {
        Company company = companyRepository.findById(SecurityUtil.getCompanyClaim())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
        return ProductCategory.builder()
                .id(dto.getId())
                .categoryName(dto.getCategoryName())
                .company(company)
                .build();
    }
}
