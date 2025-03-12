package com.manpower.repository;

import com.manpower.model.AssetProject;
import com.manpower.model.Client;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AssetProjectRepository extends JpaRepository<AssetProject, Integer> {
  List<AssetProject> findProjectsByAsset_IdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(@NotNull Integer assetId, LocalDate startDate, LocalDate endDate);

  @Query("SELECT ap FROM AssetProject ap JOIN ap.project p WHERE p.client = :client AND p.startDate <= :endDate")
  List<AssetProject> findAssetsByClientAndProjectEndDate(@Param("client") Client client,
                                                         @Param("endDate") LocalDate endDate);

}
