package com.manpower.model;

import com.manpower.common.Contants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "project_asset_sponsorship")
public class ProjectAssetSponsorship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "sponsor_id", nullable = false)
  private Sponsor sponsor;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "asset_project_id", nullable = false)
  private AssetProject assetProject;

  @Size(max = 11)
  @Column(name = "sponsorship_type", length = 11)
  private Contants.SponsorshipType sponsorshipType;

  @Column(name = "sponsorship_value", precision = 10, scale = 2)
  private BigDecimal sponsorshipValue;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  @Size(max = 11)
  @Column(name = "sponsorship_determinant", length = 11)
  private String sponsorshipDeterminant;
}