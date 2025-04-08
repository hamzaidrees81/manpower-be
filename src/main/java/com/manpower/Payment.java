package com.manpower;

import com.manpower.model.Asset;
import com.manpower.model.Invoice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull
    @Column(name = "paymentDate", nullable = false)
    private LocalDate paymentDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "paymentMethod", nullable = false, length = 50)
    private String paymentMethod;

    @Size(max = 100)
    @Column(name = "reference", length = 100)
    private String reference;

    @NotNull
    @Lob
    @Column(name = "paidToType", nullable = false)
    private String paidToType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paidToId", nullable = false)
    private Asset paidTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @Lob
    @Column(name = "remarks")
    private String remarks;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "createdAt")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updatedAt")
    private Instant updatedAt;

}