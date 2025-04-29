package com.manpower.pos.model;

import com.manpower.model.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Lob
    @Column(name = "shop_address")
    private String shopAddress;

    @Size(max = 20)
    @Column(name = "shop_phone1", length = 20)
    private String shopPhone1;

    @Size(max = 20)
    @Column(name = "shop_phone2", length = 20)
    private String shopPhone2;

    @Lob
    @Column(name = "comments")
    private String comments;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date_created")
    private Instant dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}