package com.manpower.repository;

import com.manpower.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
