package com.manpower.mapper;

import com.manpower.model.*;
import com.manpower.model.dto.ExpenseDTO;
import com.manpower.util.SecurityUtil;

public class ExpenseMapper {

    // Static method to map from Expense entity to ExpenseDTO
    public static ExpenseDTO toDTO(Expense expense) {
        if (expense == null) {
            return null; // Handle the case where the expense is null
        }

        return new ExpenseDTO(
            expense.getId(),
            expense.getAsset() != null ? expense.getAsset().getId() : null,
            expense.getExpenseType(),
            expense.getAmount(),
            expense.getExpenseProject() != null ? expense.getExpenseProject().getId() : null,
            expense.getExpenseMetric(),
            expense.getExpenseCategory() != null ? expense.getExpenseCategory().getId() : null
        );
    }


    public static Expense toEntity(ExpenseDTO dto) {
        if (dto == null) return null;

        Expense expense = new Expense();
        expense.setId(dto.getId());
        expense.setAmount(dto.getAmount());
        expense.setExpenseType(dto.getExpenseType());
        expense.setExpenseMetric(dto.getExpenseMetric());

        expense.setCompany(Company.builder()
          .id(SecurityUtil.getCompanyClaim())
          .build());

        if (dto.getAssetId() != null) {
            Asset asset = new Asset();
            asset.setId(dto.getAssetId());
            expense.setAsset(asset);
        }

        if (dto.getExpenseCategoryId() != null) {
            ExpenseCategory category = new ExpenseCategory();
            category.setId(dto.getExpenseCategoryId());
            expense.setExpenseCategory(category);
        }

        if (dto.getExpenseProjectId() != null) {
            Project project = new Project();
            project.setId(dto.getExpenseProjectId());
            expense.setExpenseProject(project);
        }

        return expense;
    }
}
