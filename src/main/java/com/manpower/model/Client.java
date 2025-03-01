package com.manpower.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "client")
public class Client {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "client_id", nullable = false, length = 50)
  private String clientId;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "address", nullable = false, length = 100)
  private String address;

}