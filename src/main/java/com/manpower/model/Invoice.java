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
@Table(name = "invoice")
public class Invoice {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "number", nullable = false, length = 50)
  private String number;

  @ColumnDefault("0")
  @Column(name = "status", nullable = false)
  private Byte status;

  @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
  private BigDecimal totalAmount;

  @Column(name = "cleared_date")
  private LocalDate clearedDate;

}