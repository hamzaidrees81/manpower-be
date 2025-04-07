package com.manpower.repository;

import com.manpower.model.ProjectAssetSponsorship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssetSponsorshipRepository  extends JpaRepository<ProjectAssetSponsorship, Long> {
  List<ProjectAssetSponsorship> findByAssetProject_Id(Integer assetProjectId);
  List<ProjectAssetSponsorship> findByAsset_Id(Integer assetId);
}
