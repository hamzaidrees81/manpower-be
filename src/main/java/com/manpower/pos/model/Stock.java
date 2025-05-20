package com.manpower.pos.model;

import com.manpower.model.Company;
import com.manpower.pos.enums.AliveStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ColumnDefault("0.00")
    @Column(name = "quantity", precision = 15, scale = 2)
    private BigDecimal quantity;

    @ColumnDefault("0.00")
    @Column(name = "min_sale_price", precision = 15, scale = 2)
    private BigDecimal minSalePrice;

    @Column(name = "retail_price", precision = 15, scale = 2)
    private BigDecimal retailPrice;

    @Column(name = "storage_rack")
    private String storageRack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AliveStatus status;

}