package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ExpenseDTO {

    private Integer id;
    private Integer assetId;
    private String  assetName;
    private Contants.ExpenseType expenseType;
    private Integer amount;
    private Integer expenseProjectId; // Project ID as you might not want to expose full object
    private String expenseProjectName;
    private Byte expenseMetric;
    private Integer expenseCategoryId; // Expense Category ID
    private String expenseCategoryName;
    private String comment;
}
