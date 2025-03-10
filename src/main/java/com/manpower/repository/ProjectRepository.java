package com.manpower.repository;

import com.manpower.model.Client;
import com.manpower.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
//  public List<Project> findProjectByClientAndDate(Client client, Date startDate, Date endDate) {
  List<Project> findProjectByClientAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Client client, LocalDate startDate, LocalDate endDate);
  List<Project> findProjectByClient(Client client);

}
