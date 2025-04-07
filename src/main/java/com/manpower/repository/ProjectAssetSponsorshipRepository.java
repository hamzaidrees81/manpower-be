package com.manpower.repository;

import com.manpower.model.ProjectAssetSponsorship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssetSponsorshipRepository  extends JpaRepository<ProjectAssetSponsorship, Long> {
  List<ProjectAssetSponsorship> findAllByAssetProject_Id(Integer assetProjectId);
  List<ProjectAssetSponsorship> findAllByAsset_Id(Integer assetId);
  List<ProjectAssetSponsorship> findProjectAssetSponsorshipsByAsset_Id(Integer assetId);

}
