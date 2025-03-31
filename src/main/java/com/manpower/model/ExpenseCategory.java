package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "expense_category")
public class ExpenseCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "expense_category_id", nullable = false)
  private Integer id;

  @Size(max = 45)
  @NotNull
  @Column(name = "category_name", nullable = false, length = 45)
  private String categoryName;

}