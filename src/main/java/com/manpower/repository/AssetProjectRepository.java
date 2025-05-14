package com.manpower.repository;

import com.manpower.model.Asset;
import com.manpower.model.AssetProject;
import com.manpower.model.Client;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AssetProjectRepository extends JpaRepository<AssetProject, Integer> {
  List<AssetProject> findProjectsByAsset_IdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(@NotNull Integer assetId, LocalDate startDate, LocalDate endDate);

  //THE DB START TIME SHOULD BE LESS THAN EQUALS THE FILTER END TIME...
  @Query("SELECT ap FROM AssetProject ap JOIN ap.project p WHERE p.client = :client AND p.startDate <= :endDate  AND ap.status = 1 ")
  List<AssetProject> findAssetsByClientAndProjectEndDate(@Param("client") Client client,
                                                         @Param("endDate") LocalDate endDate);

  List<AssetProject> findProjectsByAsset_IdAndIsActive(Integer assetId, @NotNull Byte status);
  // Method 1: Count assets where the number of active projects is at least 1 for a given company
  @Query("""
    SELECT COUNT(DISTINCT ap.asset.id) 
    FROM AssetProject ap 
    WHERE ap.isActive = 1  AND ap.status = 1 AND ap.company.id = :companyId
""")
  long countAssetsWithActiveProjects(@Param("companyId") Integer companyId);

  // Method 2: Count assets where the asset has NO active projects for a given company
  @Query("""
    SELECT COUNT(DISTINCT a.id) 
    FROM Asset a 
    WHERE a.company.id = :companyId
    AND NOT EXISTS (
        SELECT ap.id FROM AssetProject ap WHERE ap.asset.id = a.id AND ap.isActive = 1  AND ap.status = 1 AND ap.company.id = :companyId
    )
""")
  long countAssetsWithNoActiveProjects(@Param("companyId") Integer companyId);

  // Return assets where they have at least 1 active project for a given company
  @Query("""
    SELECT DISTINCT ap.asset 
    FROM AssetProject ap 
    WHERE ap.isActive = 1  AND ap.status = 1 AND ap.company.id = :companyId
""")
  List<com.manpower.model.Asset> findAssetsWithActiveProjects(@Param("companyId") Integer companyId);

  // Method 4: Return assets where they have NO active projects for a given company
  @Query("""
    SELECT a 
    FROM Asset a 
    WHERE a.company.id = :companyId
    AND NOT EXISTS (
        SELECT ap.id FROM AssetProject ap WHERE ap.asset.id = a.id AND ap.isActive = 1 AND ap.status = 1 AND ap.company.id = :companyId
    )
""")
  List<com.manpower.model.Asset> findAssetsWithNoActiveProjects(@Param("companyId") Integer companyId);

  @Query("""
    SELECT DISTINCT ap.asset 
    FROM AssetProject ap 
    WHERE ap.project.id = :projectId
    AND   ap.isActive = 1
    AND   ap.status = 1

""") //TODO: ADD COMPANY ID
  List<Asset> findAssetsByProjectId(@Param("projectId") Integer projectId);

  @Query("""
    SELECT COUNT(DISTINCT ap.asset.id) 
    FROM AssetProject ap 
    WHERE ap.project.id = :projectId
    AND   ap.isActive = 1
    AND   ap.status = 1
""") //TODO: ADD COMPANY ID
  long countAssetsByProjectId(@Param("projectId") Integer projectId);

  long countAssetProjectsByProject_Client_Id(Integer clientId);
}
