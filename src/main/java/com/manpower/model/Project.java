package com.manpower.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @Column(name = "project_id", nullable = false, length = 50)
  private String projectId;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "location", nullable = false, length = 100)
  private String location;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

}