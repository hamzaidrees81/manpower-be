package com.manpower.pos.repository;

import com.manpower.pos.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findByProduct_Id(Integer productId);
    Optional<Stock> findByProduct_IdAndShop_Id(Integer productId, Integer shopId);
    List<Stock> findAllByCompanyId(Integer companyId);
    List<Stock> findAllByProduct_IdAndShop_IdAndCompany_Id(Integer productId, Integer shopId, Integer companyId);

    List<Stock> findAllByShop_IdAndCompany_Id(Integer shopId, Integer companyClaim);
    List<Stock> findAllByProduct_IdAndCompany_Id(Integer productId, Integer companyClaim);
}

//public List<Stock> getAllStocksByProductIdShopIdCompanyId() {

