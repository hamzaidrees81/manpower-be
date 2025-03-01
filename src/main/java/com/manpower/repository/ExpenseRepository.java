package com.manpower.repository;

import com.manpower.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
