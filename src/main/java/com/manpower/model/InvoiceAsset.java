package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
@Entity
@Table(name = "invoice_asset")
@AllArgsConstructor
public class InvoiceAsset {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "invoice_id", nullable = false)
  private Invoice invoice;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @Column(name = "standard_hours", precision = 5, scale = 2)
  private BigDecimal standardHours;

  @Column(name = "standard_rate", precision = 5, scale = 2)
  private BigDecimal standardRate;

  @Column(name = "ot_rate", precision = 5, scale = 2)
  private BigDecimal otRate;

  @Column(name = "ot_hours", precision = 5, scale = 2)
  private BigDecimal otHours;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "asset_project_id")
  private AssetProject assetProject;

}