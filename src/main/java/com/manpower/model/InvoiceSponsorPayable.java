package com.manpower.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "invoice_sponsor_payable")
public class InvoiceSponsorPayable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invoice_id", nullable = false)
  private Integer id;

  @Column(name = "project_sponsorship_id")
  private Integer projectSponsorshipId;

  @Column(name = "sponsorship_payable", precision = 10, scale = 2)
  private BigDecimal sponsorshipPayable;

  @Size(max = 7)
  @Column(name = "payment_status", length = 7)
  private String paymentStatus;

  @Column(name = "status")
  private Byte status;

}