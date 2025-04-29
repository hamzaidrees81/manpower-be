package com.manpower.pos.repository;

import com.manpower.pos.model.UnitName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<UnitName, Long> {
}
