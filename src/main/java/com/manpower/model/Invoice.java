  package com.manpower.model;

  import jakarta.persistence.*;
  import jakarta.validation.constraints.NotNull;
  import jakarta.validation.constraints.Size;
  import lombok.*;
  import org.hibernate.annotations.ColumnDefault;
  import org.hibernate.annotations.OnDelete;
  import org.hibernate.annotations.OnDeleteAction;

  import java.math.BigDecimal;
  import java.time.LocalDate;

  @Data
  @Builder
  @Entity
  @Table(name = "invoice")
  @AllArgsConstructor
  @RequiredArgsConstructor
  public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Size(max = 50)
    @NotNull
    @Column(name = "number", length = 50)
    private String number;

    @NotNull
    @ColumnDefault("1")  //1. unpaid; 2. Paid
    @Column(name = "status")
    private Byte status;

    @Column(name = "cleared_date")
    private LocalDate clearedDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate; //should come from FE in Invoice Date

    @Column(name = "tax_amount", precision = 10, scale = 2)
    private BigDecimal taxAmount;

    @Column(name = "total_amount_with_tax", precision = 10, scale = 2)
    private BigDecimal totalAmountWithTax;

    @Column(name = "total_before_tax", precision = 10, scale = 2)
    private BigDecimal totalBeforeTax;

    @Column(name = "assets_payable", precision = 10, scale = 2)
    private BigDecimal assetsPayable;

    @Column(name = "sponsor_payable", precision = 10, scale = 2)
    private BigDecimal sponsorPayable;

    @Column(name = "profit", precision = 10, scale = 2)
    private BigDecimal profit;

    @Column(name = "creator_id")
    private Integer creatorId;

  }