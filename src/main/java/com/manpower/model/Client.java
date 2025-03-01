package com.manpower.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "client_id", nullable = false, length = 50)
  private String clientId;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "address", nullable = false, length = 100)
  private String address;

}