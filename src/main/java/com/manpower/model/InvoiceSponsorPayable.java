package com.manpower.model;

import com.manpower.common.Contants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice_sponsor_payable")
public class InvoiceSponsorPayable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "project_sponsorship_id", nullable = false)
  private ProjectAssetSponsorship projectSponsorshipId;

  @Column(name = "sponsorship_payable", precision = 10, scale = 2)
  private BigDecimal sponsorshipPayable;

  @Size(max = 7)
  @Column(name = "payment_status", length = 7)
  private Contants.PaymentStatus paymentStatus;

  @Column(name = "status")
  private Byte status;

  //stores which asset helped us earn it
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "sponsorship_asset")
  private Asset sponsorshipAsset;

  @Size(max = 11)
  @Column(name = "sponsorship_determinant", length = 11)
  private String sponsorshipDeterminant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sponsor_id")
  private Sponsor sponsor;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "invoice_id")
  private Invoice invoice;

}