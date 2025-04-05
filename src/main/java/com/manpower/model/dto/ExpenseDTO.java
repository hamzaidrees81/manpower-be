package com.manpower.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ExpenseDTO {

    private Integer id;
    private Integer assetId;
    private Byte expenseType;
    private Integer amount;
    private Integer expenseProjectId; // Project ID as you might not want to expose full object
    private Byte expenseMetric;
    private Integer expenseCategoryId; // Expense Category ID
}
