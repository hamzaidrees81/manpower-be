package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "project_sponsorship")
public class ProjectSponsorship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotNull
  @Column(name = "sponsor_id", nullable = false)
  private Integer sponsorId;

  @NotNull
  @Column(name = "asset_project_id", nullable = false)
  private Integer assetProjectId;

  @Size(max = 11)
  @Column(name = "sponsorship_type", length = 11)
  private String sponsorshipType;

  @Column(name = "sponsorship_value", precision = 10, scale = 2)
  private BigDecimal sponsorshipValue;

}