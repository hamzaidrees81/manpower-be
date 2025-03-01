package com.manpower.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "asset_project")
public class AssetProject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private com.manpower.model.Company company;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "project_id", nullable = false)
  private com.manpower.model.Project project;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "designation", nullable = false)
  private com.manpower.model.Designation designation;

  @Column(name = "regular_rate", nullable = false, precision = 10, scale = 2)
  private BigDecimal regularRate;

  @Column(name = "overtime_rate", nullable = false, precision = 10, scale = 2)
  private BigDecimal overtimeRate;

  @Column(name = "regular_rate_paid", nullable = false, precision = 10, scale = 2)
  private BigDecimal regularRatePaid;

  @Column(name = "overtime_rate_paid", nullable = false, precision = 10, scale = 2)
  private BigDecimal overtimeRatePaid;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @ColumnDefault("1")
  @Column(name = "is_active", nullable = false)
  private Byte isActive;

  @ColumnDefault("0")
  @Column(name = "status", nullable = false)
  private Byte status;

}