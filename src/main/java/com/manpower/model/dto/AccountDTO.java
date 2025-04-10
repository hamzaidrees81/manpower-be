package com.manpower.model.dto;

import com.manpower.common.AccountConstant;
import com.manpower.common.Contants;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class AccountDTO {
    private Integer id;
    private String name;
    private AccountConstant.AccountType type;
    private String description;
    private BigDecimal balance;
    private BigDecimal accountNumber;
    private String iban;
    private String bankName;
    private Contants.isDefaultAccount isDefaultAccount;
    private Contants.Status status;
    private Instant createdAt;
    private Instant updatedAt;
}

