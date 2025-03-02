package com.manpower.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "timesheet")
public class Timesheet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_project_id", nullable = false)
  private AssetProject assetProject;

  @Column(name = "timesheet_date", nullable = false)
  private LocalDate timesheetDate;

  @Column(name = "rate_type", nullable = false)
  private Byte rateType;

  @Column(name = "hours", nullable = false, precision = 5, scale = 2)
  private BigDecimal hours;

  @Column(name = "invoice_number", nullable = false)
  private Integer invoiceNumber;

}