package com.manpower.pos.repository;

import com.manpower.pos.enums.RelatedEntityType;
import com.manpower.pos.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {
    List<StockMovement> findByRelatedEntityTypeAndRelatedEntityId(RelatedEntityType relatedEntityType, Integer relatedEntityId);
}
