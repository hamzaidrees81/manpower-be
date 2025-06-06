package com.manpower.pos.repository;

import com.manpower.pos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCompany_Id(Integer companyId);
    Optional<Product> findByIdAndCompany_Id(Integer id, Integer company_id);
}
