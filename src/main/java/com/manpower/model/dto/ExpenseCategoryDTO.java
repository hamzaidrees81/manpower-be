package com.manpower.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExpenseCategoryDTO {
    private Integer id;
    private String categoryName;

    // Optional constructor for convenience
    public ExpenseCategoryDTO(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
