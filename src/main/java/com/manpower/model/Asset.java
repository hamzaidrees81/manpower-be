package com.manpower.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "asset")
public class Asset {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private com.manpower.model.Company company;

  @ManyToOne(fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "sponsored_by")
  private com.manpower.model.Sponsor sponsoredBy;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "id_number", nullable = false, length = 15)
  private String idNumber;

  @Column(name = "iqama_expiry")
  private LocalDate iqamaExpiry;

  @Column(name = "phone", length = 25)
  private String phone;

  @Column(name = "designation", length = 25)
  private String designation;

  @Column(name = "passport")
  private String passport;

  @Column(name = "passport_expiry")
  private LocalDate passportExpiry;

  @Column(name = "joining_date", nullable = false)
  private LocalDate joiningDate;

  @Column(name = "asset_type", nullable = false)
  private Byte assetType;

  @Column(name = "asset_number", nullable = false)
  private Integer assetNumber; //TODO: WHAT IS THIS?

  @Column(name = "asset_ownership", nullable = false)
  private Byte assetOwnership;

  @Column(name = "sponsorship_value", precision = 3, scale = 2)
  private BigDecimal sponsorshipValue;

  @Column(name = "sponsorship_type")
  private String sponsorshipType;
}