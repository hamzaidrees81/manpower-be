package com.manpower.pos.dto;

import com.manpower.pos.enums.STATUS;
import com.manpower.pos.model.Brand;
import com.manpower.pos.model.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String name;
    private String namePrint;
    private String namePrintAr;
    private String code;
    private String productCode;
    private String productType;
    private String comments;
    private STATUS active;
    private Integer categoryId;
    private ProductCategoryDto category;
    private BrandDto brand;
    private Integer brandId;
}
