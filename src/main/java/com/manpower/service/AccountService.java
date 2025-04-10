package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.mapper.AccountMapper;
import com.manpower.model.Account;
import com.manpower.model.dto.AccountDTO;
import com.manpower.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository mainAccountRepository;

    public List<AccountDTO> getAllAccounts(Integer companyId) {
        return mainAccountRepository.findAllByCompanyId(companyId).stream().map(AccountMapper::toDTO).collect(Collectors.toList());
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {

        Account account = mainAccountRepository.save(AccountMapper.toEntity(accountDTO));
        return AccountMapper.toDTO(account);
    }

    public AccountDTO updateAccountBalance(Integer accountId, BigDecimal newBalance) {
        Account account = mainAccountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(newBalance);
        Account account1 = mainAccountRepository.save(account);
        return AccountMapper.toDTO(account1);
    }

    public void deleteAccount(Integer accountId) {
        mainAccountRepository.deleteById(accountId);
    }

    public Account getAccountById(Integer id) {
        return mainAccountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account getDefaultAccount(Integer companyId) {
        return mainAccountRepository.findFirstByCompanyIdAndIsDefaultEquals(companyId, Contants.isDefaultAccount.YES.getValue())
                .orElse(null);

    }
}
