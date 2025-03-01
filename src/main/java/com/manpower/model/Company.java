package com.manpower.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "company")
public class Company {
  @Id
  @Column(name = "id", nullable = true)
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "address", nullable = false, length = 100)
  private String address;

  @Column(name = "max_asset_count", nullable = false)
  private Integer maxAssetCount;

}