package com.manpower.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "username", nullable = false, length = 45)
  private String username;

  @Column(name = "password", length = 45)
  private String password;

  @Column(name = "create_date", length = 45)
  private String createDate;

  @Column(name = "update_date", length = 45)
  private String updateDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id")
  private Company company;

}