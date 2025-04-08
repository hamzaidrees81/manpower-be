package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_payable")
public class AssetPayable {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "asset_project_id")
  private AssetProject assetProject;

  @Column(name = "asset_payable", precision = 10, scale = 2)
  private BigDecimal assetPayable;

  @Size(max = 10)
  @Column(name = "payment_status", length = 10)
  private String paymentStatus;

  @Column(name = "status")
  private Byte status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "asset_id")
  private Asset asset;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "invoice_id")
  private Invoice invoice;

  @NotNull
  @Column(name = "paidAmount", nullable = false, precision = 10, scale = 2)
  private BigDecimal paidAmount;

  @NotNull
  @Column(name = "company_id", nullable = false)
  private Integer companyId;

}