package com.manpower.pos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "main_account")
public class MainAccount {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @Lob
    @Column(name = "description")
    private String description;

    @ColumnDefault("0.00")
    @Column(name = "balance", precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "account_number", precision = 15, scale = 2)
    private BigDecimal accountNumber;

    @Size(max = 50)
    @Column(name = "iban", length = 50)
    private String iban;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

}