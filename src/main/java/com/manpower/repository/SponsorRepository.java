package com.manpower.repository;

import com.manpower.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
}
