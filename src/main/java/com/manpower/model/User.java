package com.manpower.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Size(max = 45)
  @NotNull
  @Column(name = "username", nullable = false, length = 45)
  private String username;

  @Size(max = 45)
  @NotNull
  @Column(name = "password", nullable = false, length = 45)
  private String password;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  //@JsonIgnoreProperties("users") // Prevent infinite recursion //LEARN IT
  private Company company;

  @ColumnDefault("12")
  @Column(name = "role")
  private String role;

  @ColumnDefault("1")
  @Column(name = "status")
  private Integer status;

  @Column(name = "create_date", updatable = false)
  @CreationTimestamp
  private Instant createDate;

  @Column(name = "update_date")
  @UpdateTimestamp
  private Instant updateDate;


}