package com.manpower.controller;

import com.manpower.model.Account;
import com.manpower.model.dto.AccountDTO;
import com.manpower.service.MainAccountService;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final MainAccountService mainAccountService;

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        Integer companyId = SecurityUtil.getCompanyClaim();
        return mainAccountService.getAllAccounts(companyId);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Integer id) {
        return mainAccountService.getAccountById(id);
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountDTO account) {
        return mainAccountService.createAccount(account);
    }

    @PutMapping("/{id}/balance")
    public AccountDTO updateBalance(@PathVariable Integer id, @RequestParam BigDecimal newBalance) {
        return mainAccountService.updateAccountBalance(id, newBalance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        mainAccountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}
