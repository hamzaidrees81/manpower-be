package com.manpower.pos.model;

import com.manpower.model.Company;
import com.manpower.pos.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "stock_movement")
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", precision = 15, scale = 2)
    private BigDecimal quantity;

    @Column(name = "remaining_qty", precision = 15, scale = 2)
    private BigDecimal remQty;

    @Column(name = "buy_price", precision = 15, scale = 2)
    private BigDecimal buyPrice;

    @Column(name = "retail_price", precision = 15, scale = 2)
    private BigDecimal retailPrice;

    @Column(name = "sold_price", precision = 15, scale = 2)
    private BigDecimal soldPrice;

    @Column(name = "min_price", precision = 15, scale = 2)
    private BigDecimal minPrice;

    @Column(name = "batch")
    private String batch;

    @Column(name = "comments")
    private String comments;

    @Column(name = "storage_rack")
    private String storageRack;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private StockMovementType movementType;

    @Enumerated(EnumType.STRING)
    @Column(name = "reason", nullable = false)
    private StockMovementReason movementReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_strategy")
    private PricingStrategy pricingStrategy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "movement_date")
    private Instant movementDate;

    @Column(name = "related_entity_id")
    private Integer relatedEntityId;

    @NotNull
    @Column(name = "vat_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal vatAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "related_entity_type", nullable = false)
    private RelatedEntityType relatedEntityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AliveStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
