package com.manpower.repository;

import com.manpower.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PreferencesRepository extends JpaRepository<Preference, Integer> {
  Preference findByCompany_Id(String companyId);
}
