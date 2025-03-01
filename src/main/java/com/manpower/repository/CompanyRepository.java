package com.manpower.repository;

import com.manpower.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
