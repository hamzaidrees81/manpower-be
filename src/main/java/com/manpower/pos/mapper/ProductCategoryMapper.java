package com.manpower.pos.mapper;

import com.manpower.model.Company;
import com.manpower.pos.dto.ProductCategoryDto;
import com.manpower.pos.model.ProductCategory;
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
        dto.setCompanyId(category.getCompany().getId());
        return dto;
    }

    public ProductCategory toEntity(ProductCategoryDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
        return ProductCategory.builder()
                .id(dto.getId())
                .categoryName(dto.getCategoryName())
                .company(company)
                .build();
    }
}
