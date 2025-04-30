package com.manpower.pos.model;

import com.manpower.model.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date")
    private Instant date;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems = new ArrayList<>();

}