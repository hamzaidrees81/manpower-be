package com.manpower.pos.mapper;

import com.manpower.model.Company;
import com.manpower.pos.dto.ProductDto;
import com.manpower.pos.enums.STATUS;
import com.manpower.pos.model.Brand;
import com.manpower.pos.model.Product;
import com.manpower.pos.model.ProductCategory;
import com.manpower.util.SecurityUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    private final ProductCategoryMapper productCategoryMapper;
    private final BrandMapper brandMapper;

    public ProductMapper(ProductCategoryMapper productCategoryMapper, BrandMapper brandMapper) {
        this.productCategoryMapper = productCategoryMapper;
        this.brandMapper = brandMapper;
    }

    public ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setNamePrint(product.getNamePrint());
        dto.setNamePrintAr(product.getNamePrintAr());
        dto.setCode(product.getCode());
        dto.setProductCode(product.getProductCode());
        dto.setProductType(product.getProductType());
        dto.setComments(product.getComments());
        dto.setStatus(product.getStatus());
        dto.setCategory(product.getCategory() != null ? productCategoryMapper.toDto(product.getCategory()) : null);
        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        dto.setBrand(product.getBrand() != null ? brandMapper.toDto(product.getBrand()) : null);
        dto.setBrandId(product.getBrand() != null ? product.getBrand().getId() : null);
        dto.setSellingPrice(product.getSellingPrice());
        dto.setStockQty(
                product.getStocks().stream()
                        .map(stock -> stock.getQuantity())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );        return dto;
    }

    public Product toEntity(ProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setNamePrint(dto.getNamePrint());
        product.setNamePrintAr(dto.getNamePrintAr());
        product.setCode(dto.getCode());
        product.setProductCode(dto.getProductCode());
        product.setProductType(dto.getProductType());
        product.setComments(dto.getComments());
        product.setStatus(dto.getStatus());
        product.setCompany(Company.builder().id(SecurityUtil.getCompanyClaim()).build());
        product.setCategory(ProductCategory.builder().id(dto.getCategoryId()).build());
        product.setBrand(Brand.builder().id(dto.getBrandId()).build());
        product.setSellingPrice(dto.getSellingPrice());
        return product;
    }
}
