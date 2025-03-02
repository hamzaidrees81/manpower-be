package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "designation", nullable = false)
  private Designation designation;

  @Size(max = 45)
  @Column(name = "asset_project_name", length = 45)
  private String assetProjectName;

  @NotNull
  @Column(name = "regular_rate", nullable = false, precision = 10, scale = 2)
  private BigDecimal regularRate;

  @NotNull
  @Column(name = "overtime_rate", nullable = false, precision = 10, scale = 2)
  private BigDecimal overtimeRate;

  @NotNull
  @Column(name = "regular_rate_paid", nullable = false, precision = 10, scale = 2)
  private BigDecimal regularRatePaid;

  @NotNull
  @Column(name = "overtime_rate_paid", nullable = false, precision = 10, scale = 2)
  private BigDecimal overtimeRatePaid;

  @NotNull
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @NotNull
  @ColumnDefault("1")
  @Column(name = "is_active", nullable = false)
  private Byte isActive;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "status", nullable = false)
  private Byte status;

}