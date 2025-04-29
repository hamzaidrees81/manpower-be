    package com.manpower.pos.model;

    import com.manpower.model.Company;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;
    import lombok.*;

    @Data
    @Getter
    @Setter
    @Entity
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    @Table(name = "product_category")
    public class ProductCategory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @Size(max = 50)
        @NotNull
        @Column(name = "category_name", nullable = false, length = 50)
        private String categoryName;

        @NotNull
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "company_id", nullable = false)
        private Company company;

    }