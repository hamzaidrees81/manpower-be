package com.manpower.service;

import com.manpower.model.Expense;
import com.manpower.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Integer id) {
        return expenseRepository.findById(id);
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Integer id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Integer id, Expense expense) {
        throw new RuntimeException("Not implemented yet");

    }
}
