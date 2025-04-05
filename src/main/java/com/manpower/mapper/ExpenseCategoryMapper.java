package com.manpower.mapper;

import com.manpower.model.ExpenseCategory;
import com.manpower.model.dto.ExpenseCategoryDTO;

public class ExpenseCategoryMapper {

    public static ExpenseCategoryDTO toDTO(ExpenseCategory category) {
        if (category == null) {
            return null;
        }

        return new ExpenseCategoryDTO(
            category.getId(),
            category.getCategoryName()
        );
    }
}
