package com.manpower.model;

import com.manpower.common.Contants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "project_sponsorship_id", nullable = false)
  private ProjectSponsorship projectSponsorshipId;

  @Column(name = "sponsorship_payable", precision = 10, scale = 2)
  private BigDecimal sponsorshipPayable;

  @Size(max = 7)
  @Column(name = "payment_status", length = 7)
  private Contants.PaymentStatus paymentStatus;

  @Column(name = "status")
  private Byte status;



}