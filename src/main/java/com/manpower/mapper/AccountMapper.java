package com.manpower.mapper;

import com.manpower.common.AccountConstant;
import com.manpower.common.Contants;
import com.manpower.model.dto.AccountDTO;
import com.manpower.model.Account;
import com.manpower.util.SecurityUtil;

public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        if (account == null) return null;

        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setType(AccountConstant.AccountType.valueOf(account.getType()));
        dto.setDescription(account.getDescription());
        dto.setBalance(account.getBalance());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setIban(account.getIban());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setUpdatedAt(account.getUpdatedAt());
        dto.setStatus(Contants.Status.fromValue(account.getStatus()));
        dto.setBankName(account.getBankName());
        dto.setIsDefaultAccount(Contants.isDefaultAccount.fromValue(account.getIsDefault()));

        return dto;
    }

    public static Account toEntity(AccountDTO dto) {
        if (dto == null) return null;

        Account account = new Account();
        account.setId(dto.getId());
        account.setCompanyId(SecurityUtil.getCompanyClaim());
        account.setName(dto.getName());
        account.setType(dto.getType().name());
        account.setDescription(dto.getDescription());
        account.setBalance(dto.getBalance());
        account.setAccountNumber(dto.getAccountNumber());
        account.setIban(dto.getIban());
        account.setCreatedAt(dto.getCreatedAt());
        account.setUpdatedAt(dto.getUpdatedAt());

        return account;
    }
}
