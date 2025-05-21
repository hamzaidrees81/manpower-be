package com.manpower.pos.repository;

import com.manpower.pos.enums.RelatedEntityType;
import com.manpower.pos.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {
    List<StockMovement> findByRelatedEntityTypeAndRelatedEntityId(RelatedEntityType relatedEntityType, Integer relatedEntityId);
    Optional<StockMovement> findTopByProduct_IdAndShop_IdAndCompany_IdOrderByIdDesc(Integer product_Id, Integer shop_Id, Integer company_Id);
    List<StockMovement> findAllByProduct_IdAndShop_IdAndCompany_IdAndRemQtyGreaterThanOrderByIdAsc(Integer product_Id, Integer shop_Id, Integer company_Id, BigDecimal remQty);
    Optional<StockMovement> findTopByProduct_IdAndCompany_IdOrderByIdDesc(Integer product_Id, Integer company_Id);
}
