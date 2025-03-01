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
@Table(name = "asset")
public class Asset {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "company_id", nullable = false)
  private com.manpower.model.Company company;

  @ManyToOne(fetch = FetchType.LAZY)
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
  private Integer passport;

  @Column(name = "passport_expiry")
  private LocalDate passportExpiry;

  @Column(name = "joining_date", nullable = false)
  private LocalDate joiningDate;

  @Column(name = "asset_type", nullable = false)
  private Byte assetType;

  @Column(name = "asset_number", nullable = false)
  private Integer assetNumber;

}