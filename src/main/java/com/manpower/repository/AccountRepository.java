package com.manpower.repository;

import com.manpower.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByNameAndCompanyId(String name, Integer companyId);
    List<Account> findAllByCompanyId(Integer companyId);
    Optional<Account> findFirstByCompanyIdAndIsDefaultEquals(Integer companyId, Byte isDefault);
}
