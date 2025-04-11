package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @Column(name = "expense_type", nullable = false)
  private String expenseType; //self or project based

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @ManyToOne(fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "expense_project")
  private com.manpower.model.Project expenseProject;

  @Column(name = "expense_metric")
  private Byte expenseMetric;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "expense_category", nullable = false)
  private ExpenseCategory expenseCategory;

  @Size(max = 100)
  @NotNull
  @Column(name = "comments", nullable = false, length = 100)
  private String comments;

}