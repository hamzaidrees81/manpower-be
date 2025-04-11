package com.manpower.service;

import com.manpower.mapper.ExpenseMapper;
import com.manpower.model.Expense;
import com.manpower.model.dto.ExpenseDTO;
import com.manpower.repository.ExpenseRepository;
import com.manpower.util.SecurityUtil;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CompanyService companyService;

    public ExpenseService(ExpenseRepository expenseRepository, CompanyService companyService) {
        this.expenseRepository = expenseRepository;
        this.companyService = companyService;
    }

    public List<ExpenseDTO> filterExpenses(Integer assetId, Integer projectId, Integer categoryId) {
        List<Expense> filtered = expenseRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (assetId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("asset").get("id"), assetId));
            }

            if (projectId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("expenseProject").get("id"), projectId));
            }

            if (categoryId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("expenseCategory").get("id"), categoryId));
            }

            return predicate;
        });

        return filtered.stream().map(ExpenseMapper::toDTO).collect(Collectors.toList());
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
