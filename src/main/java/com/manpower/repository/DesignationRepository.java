package com.manpower.repository;

import com.manpower.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation, Integer> {
}
