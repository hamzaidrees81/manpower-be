package com.manpower.pos.dto;

import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.enums.STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String namePrint;
    private String namePrintAr;
    private String code;
    private String productCode;
    private String productType;
    private String comments;
    private AliveStatus status;
    private Integer categoryId;
    private ProductCategoryDto category;
    private BrandDto brand;
    private Integer brandId;
    private BigDecimal sellingPrice;
    private BigDecimal stockQty;
}
