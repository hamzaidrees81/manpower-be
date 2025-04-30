package com.manpower.pos.model;

import com.manpower.model.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "sale_item")
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "quantity", nullable = false, precision = 15, scale = 2)
    private BigDecimal quantity;

    @NotNull
    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "purchase_price", precision = 15, scale = 2)
    private BigDecimal purchasePrice;

    @ColumnDefault("0.00")
    @Column(name = "discount", precision = 15, scale = 2)
    private BigDecimal discount;

    @ColumnDefault("0.00")
    @Column(name = "tax", precision = 15, scale = 2)
    private BigDecimal tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}