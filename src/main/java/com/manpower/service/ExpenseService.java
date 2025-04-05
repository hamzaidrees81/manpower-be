package com.manpower.service;

import com.manpower.mapper.ExpenseMapper;
import com.manpower.model.Expense;
import com.manpower.model.dto.ExpenseDTO;
import com.manpower.repository.ExpenseRepository;
import com.manpower.util.SecurityUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CompanyService companyService;

    public ExpenseService(ExpenseRepository expenseRepository, CompanyService companyService) {
        this.expenseRepository = expenseRepository;
        this.companyService = companyService;
    }

    public List<ExpenseDTO> getAllExpenses() {
        Integer companyId = SecurityUtil.getCompanyClaim();
        return expenseRepository.findAllExpensesByCompany_Id(companyId).stream().map(ExpenseMapper::toDTO).toList();
    }

    public Optional<Expense> getExpenseById(Integer id) {
        return expenseRepository.findById(id);
    }

    public Expense createExpense(ExpenseDTO expense) {
        return expenseRepository.save(ExpenseMapper.toEntity(expense));
    }

    public void deleteExpense(Integer id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Integer id, Expense expense) {
        throw new RuntimeException("Not implemented yet");

    }
}
