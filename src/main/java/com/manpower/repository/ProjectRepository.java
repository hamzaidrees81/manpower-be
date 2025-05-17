package com.manpower.repository;

import com.manpower.model.Client;
import com.manpower.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
//  public List<Project> findProjectByClientAndDate(Client client, Date startDate, Date endDate) {

  //on-going project is a project which end date is greater or equal to our start date
  List<Project> findProjectByClientAndEndDateGreaterThanEqual(Client client, LocalDate startDate);

  List<Project> findProjectByClient(Client client);

  List<Project> findProjectByCompany_Id(Integer companyId);
}
