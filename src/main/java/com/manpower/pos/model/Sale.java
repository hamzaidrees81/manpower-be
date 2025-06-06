package com.manpower.pos.model;

import com.manpower.model.Client;
import com.manpower.model.Company;
import com.manpower.model.User;
import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.enums.PaymentMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date")
    private Instant date;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "vat_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal vatAmount;

    @NotNull
    @Column(name = "paid_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal paidAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AliveStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User sellingUser;

    @Size(max = 50)
    @Column(name = "po_number", length = 50)
    private String poNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull
    @Column(name = "total_before_vat", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalBeforeVat;

    @NotNull
    @Column(name = "discount_percentage", nullable = false, precision = 15, scale = 2)
    private BigDecimal discountPercentage;


    @Column(name = "sale_date")
    private Instant saleDate;

    @NotNull
    @Column(name = "received_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal receivedAmount;

    @Size(max = 20)
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false, length = 20)
    private PaymentMode paymentMode;

    @Size(max = 20)
    @NotNull
    @Column(name = "invoice_number", nullable = false, length = 20)
    private String invoiceNumber;

}