package com.manpower.repository;

import com.manpower.model.AssetProject;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AssetProjectRepository extends JpaRepository<AssetProject, Integer> {
  List<AssetProject> findProjectsByAsset_IdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(@NotNull Integer assetId, LocalDate startDate, LocalDate endDate);
}
