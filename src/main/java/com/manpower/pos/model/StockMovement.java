package com.manpower.pos.model;

import com.manpower.model.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "stock_movement")
public class StockMovement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", precision = 15, scale = 2)
    private BigDecimal quantity;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @NotNull
    @Lob
    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @NotNull
    @Lob
    @Column(name = "reason", nullable = false)
    private String reason;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "movement_date")
    private Instant movementDate;

    @Column(name = "related_entity_id")
    private Integer relatedEntityId;

    @Size(max = 50)
    @Column(name = "related_entity_type", length = 50)
    private String relatedEntityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}