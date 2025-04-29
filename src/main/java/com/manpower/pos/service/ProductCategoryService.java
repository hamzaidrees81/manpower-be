package com.manpower.pos.service;

import com.manpower.model.Company;
import com.manpower.pos.dto.ProductCategoryDto;
import com.manpower.pos.mapper.ProductCategoryMapper;
import com.manpower.pos.model.ProductCategory;
import com.manpower.pos.repository.ProductCategoryRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryDto save(ProductCategoryDto productCategoryDto) {
        ProductCategory category = productCategoryMapper.toEntity(productCategoryDto);

        // Set the company from SecurityUtil
        Company company = new Company();
        company.setId(SecurityUtil.getCompanyClaim());
        category.setCompany(company);

        category = productCategoryRepository.save(category);
        return productCategoryMapper.toDto(category);
    }

    public List<ProductCategoryDto> findAll() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductCategoryDto findById(Integer id) {
        return productCategoryRepository.findById(Long.valueOf(id))
                .map(productCategoryMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Product category not found"));
    }

    public void deleteById(Integer id) {
        productCategoryRepository.deleteById(Long.valueOf(id));
    }
}
