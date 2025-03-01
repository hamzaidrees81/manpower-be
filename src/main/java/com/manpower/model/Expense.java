package com.manpower.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "expense")
public class Expense {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @Column(name = "expense_type", nullable = false)
  private Byte expenseType;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "expense_project")
  private com.manpower.model.Project expenseProject;

  @Column(name = "expense_metric")
  private Byte expenseMetric;

}