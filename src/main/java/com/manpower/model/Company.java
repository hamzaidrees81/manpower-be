package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "address", nullable = false, length = 100)
  private String address;

  @Column(name = "max_asset_count", nullable = false)
  private Integer maxAssetCount;

  @Size(max = 100)
  @Column(name = "header_image_url", length = 100)
  private String headerImageUrl;

  @Size(max = 100)
  @Column(name = "footer_image_url", length = 100)
  private String footerImageUrl;

  @Size(max = 45)
  @Column(name = "bank_account_title", length = 45)
  private String bankAccountTitle;

  @Size(max = 45)
  @Column(name = "bank_account_number", length = 45)
  private String bankAccountNumber;

  @Size(max = 45)
  @Column(name = "bank_iban", length = 45)
  private String bankIban;

  @Size(max = 45)
  @Column(name = "bank_name", length = 45)
  private String bankName;

  @Column(name = "status")
  private Integer status;

    @Size(max = 20)
    @NotNull
    @Column(name = "vat", nullable = false, length = 20)
    private String vat;

}