package com.manpower.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_account_id")
    private Account mainAccount;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Size(max = 50)
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Size(max = 100)
    @Column(name = "reference", length = 100)
    private String reference;

    @Lob
    @Column(name = "paid_to_type")
    private String paidToType;

    @Column(name = "paid_to_id")
    private Integer paidToId;

    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Lob
    @Column(name = "remarks")
    private String remarks;

    @ColumnDefault("'COMPLETED'")
    @Lob
    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "payment_type")
    private String paymentType;

    @NotNull
    @Column(name = "payment_timestamp", nullable = false)
    private Instant paymentTimestamp;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

}