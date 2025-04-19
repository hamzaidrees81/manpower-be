package com.manpower.service;

import com.manpower.mapper.ExpenseMapper;
import com.manpower.model.Expense;
import com.manpower.model.ExpenseCategory;
import com.manpower.model.dto.ExpenseDTO;
import com.manpower.repository.ExpenseCategoryRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public List<ExpenseCategory> getAllCategories() {
        return expenseCategoryRepository.findAll();
    }

    public Optional<ExpenseCategory> getExpenseCategoryById(Long id) {
        return expenseCategoryRepository.findById(id);
    }

    public Expense createExpense(ExpenseDTO expense) {
        throw new RuntimeException("Not implemented yet");
    }

    public void deleteExpense(Integer id) {
        throw new RuntimeException("Not implemented yet");

    }

    public Expense updateExpense(Integer id, Expense expense) {
        throw new RuntimeException("Not implemented yet");

    }
}
