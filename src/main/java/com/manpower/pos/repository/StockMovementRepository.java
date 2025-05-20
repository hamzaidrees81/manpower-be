package com.manpower.pos.repository;

import com.manpower.pos.enums.RelatedEntityType;
import com.manpower.pos.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {
    List<StockMovement> findByRelatedEntityTypeAndRelatedEntityId(RelatedEntityType relatedEntityType, Integer relatedEntityId);
    Optional<StockMovement> findTopByProduct_IdAndShop_IdAndCompany_IdOrderByIdDesc(Integer product_Id, Integer shop_Id, Integer company_Id);
    Optional<StockMovement> findTopByProduct_IdAndCompany_IdOrderByIdDesc(Integer product_Id, Integer company_Id);
}
