package com.manpower.model;

import com.manpower.pos.enums.InvoiceSequenceFormula;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "preferences")
public class Preference {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @Column(name = "tax_amount", precision = 10, scale = 2)
  private BigDecimal taxAmount;

  @Column(name = "erp_invoice_seq")
  private Integer ERPinvoiceSeq;

  @Column(name = "asset_id_sequence")
  private Integer assetIdSeq;

  @Column(name = "user_id_sequence")
  private Integer userIdSeq;

  @NotNull
  @Column(name = "sale_invoice_seq")
  private Integer saleInvoiceSeq;

  @Size(max = 10)
  @Enumerated(EnumType.STRING)
  @Column(name = "use_same_sequence", nullable = false, length = 10)
  private InvoiceSequenceFormula sequenceFormula;
}