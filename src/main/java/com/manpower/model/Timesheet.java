package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "asset_project_id", nullable = false)
  private AssetProject assetProject;

  @NotNull
  @Column(name = "timesheet_date", nullable = false)
  private LocalDate timesheetDate;

  @NotNull
  @Column(name = "rate_type", nullable = false)
  private Byte rateType;

  @NotNull
  @Column(name = "hours", nullable = false, precision = 5, scale = 2)
  private BigDecimal hours;

  @Column(name = "rate", precision = 5, scale = 2)
  private BigDecimal rate;

  @Column(name = "rate_paid", precision = 5, scale = 2)
  private BigDecimal ratePaid;

  @NotNull
  @Column(name = "invoice_number", nullable = false)
  private Integer invoiceNumber;

  @Column(name = "row_sr_no")
  private Integer rowSrNo;

  @Column(name = "week_index")
  private Integer weekIndex;

}