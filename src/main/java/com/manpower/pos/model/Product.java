package com.manpower.pos.model;

import com.manpower.model.Company;
import com.manpower.pos.enums.AliveStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 250)
    @NotNull
    @Column(name = "name_print", nullable = false, length = 250)
    private String namePrint;

    @Size(max = 255)
    @NotNull
    @Column(name = "name_print_ar", nullable = false)
    private String namePrintAr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_id")
    private ProductCategory category;

    @Column(name = "subcat_id", precision = 60)
    private BigDecimal subcatId;

    @Size(max = 255)
    @Column(name = "code")
    private String code;

    @Size(max = 255)
    @Column(name = "product_code")
    private String productCode;

    @Size(max = 255)
    @Column(name = "product_type")
    private String productType;

    @Column(name = "group_id")
    private Integer groupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "packaging_id")
    private Integer packagingId;

    @Lob
    @Column(name = "comments")
    private String comments;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AliveStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "selling_price", precision = 15, scale = 2)
    private BigDecimal sellingPrice;

    @OneToMany(mappedBy = "product")
    private Set<ProductUnit> productUnits = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Stock> stocks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<StockMovement> stockMovements = new LinkedHashSet<>();

}