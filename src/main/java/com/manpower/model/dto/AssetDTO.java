package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AssetDTO {

  private Integer id;
  private Integer sponsoredById;
  private String sponsoredName;
  private String name;
  private String idNumber;
  private LocalDate iqamaExpiry;
  private String phone;
  private String designation;
  private String passport;
  private LocalDate passportExpiry;
  private LocalDate joiningDate;
  private Contants.AssetType assetType;
  private Integer assetNumber;
  private Contants.AssetOwnership assetOwnership;
}
