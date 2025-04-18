package com.manpower.controller;

import com.manpower.mapper.ExpenseMapper;
import com.manpower.model.Expense;
import com.manpower.model.ExpenseCategory;
import com.manpower.model.dto.ExpenseDTO;
import com.manpower.service.ExpenseCategoryService;
import com.manpower.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseCategoryService expenseCategoryService;

    public ExpenseController(ExpenseService expenseService, ExpenseCategoryService expenseCategoryService) {
        this.expenseService = expenseService;
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping
    public List<ExpenseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ExpenseDTO>> filterExpenses(
            @RequestParam(required = false) Integer assetId,
            @RequestParam(required = false) Integer expenseProjectId,
            @RequestParam(required = false) Integer expenseCategoryId) {

        List<ExpenseDTO> results = expenseService.filterExpenses(assetId, expenseProjectId, expenseCategoryId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/categories")
    public List<ExpenseCategory> getAllExpenseCategories() {
        return expenseCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Integer id) {
        return expenseService.getExpenseById(id)
          .map(expense -> ResponseEntity.ok(ExpenseMapper.toDTO(expense)))
          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExpenseDTO createExpense(@RequestBody ExpenseDTO expense) {
        return expenseService.createExpense(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Integer id, @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
