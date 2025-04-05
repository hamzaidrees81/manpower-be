package com.manpower.repository;

import com.manpower.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
  List<Expense> findAllExpensesByCompany_Id(Integer companyId);
}
