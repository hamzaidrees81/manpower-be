package com.manpower.controller;

import com.manpower.model.ExpenseCategory;
import com.manpower.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseCategoryService expenseCategoryService;

    @GetMapping("/categories")
    public List<ExpenseCategory> getAllExpenseCategories() {
        return expenseCategoryService.getAllCategories();
    }

}

