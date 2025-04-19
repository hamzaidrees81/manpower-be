package com.manpower.mapper;

import com.manpower.common.Contants;
import com.manpower.model.*;
import com.manpower.model.dto.ProjectAssetSponsorshipDTO;
import com.manpower.model.dto.SponsorDTO;

public class ProjectAssetSponsorshipMapper {

    public static ProjectAssetSponsorshipDTO toDTO(ProjectAssetSponsorship entity) {
        if (entity == null) return null;

        return ProjectAssetSponsorshipDTO.builder()
                .id(entity.getId())
                .sponsor(entity.getSponsor() != null ? new SponsorDTO(entity.getSponsor().getId(), entity.getSponsor().getName()): null)
                .sponsorId(entity.getSponsor() != null ? entity.getSponsor().getId() : null)
                .sponsorName(entity.getSponsor() != null ? entity.getSponsor().getName() : null)
                .assetProjectId(entity.getAssetProject() != null ? entity.getAssetProject().getId() : null)
                .assetProjectName(entity.getAssetProject() != null ? entity.getAssetProject().getAssetProjectName() : null)
                .sponsorshipType(entity.getSponsorshipType() != null ? Contants.SponsorshipType.valueOf(entity.getSponsorshipType()) : null)
                .sponsorshipValue(entity.getSponsorshipValue())
                .assetId(entity.getAsset() != null ? entity.getAsset().getId() : null)
                .assetName(entity.getAsset() != null ? entity.getAsset().getName() : null)
                .sponsorshipDeterminant(entity.getSponsorshipDeterminant() != null ? Contants.SponsorshipDeterminant.valueOf(entity.getSponsorshipDeterminant()) : null)
                .sponsorshipBasis(entity.getSponsorshipBasis() != null ? Contants.SponsorshipBasis.valueOf(entity.getSponsorshipBasis()) : null)

                .build();
    }

    public static ProjectAssetSponsorship toEntity(ProjectAssetSponsorshipDTO dto) {
        if (dto == null) return null;

        ProjectAssetSponsorship entity = new ProjectAssetSponsorship();
        entity.setId(dto.getId());

        // Only setting ID references here – JPA will recognize them if you attach them via EntityManager
        Sponsor sponsor = new Sponsor();
        sponsor.setId(dto.getSponsorId());
        entity.setSponsor(sponsor);

        AssetProject assetProject = new AssetProject();
        assetProject.setId(dto.getAssetProjectId());
        entity.setAssetProject(assetProject);

        Asset asset = new Asset();
        asset.setId(dto.getAssetId());
        entity.setAsset(asset);

        entity.setSponsorshipType(dto.getSponsorshipType().name());
        entity.setSponsorshipValue(dto.getSponsorshipValue());
        entity.setSponsorshipDeterminant(dto.getSponsorshipDeterminant().name());
        entity.setSponsorshipBasis(dto.getSponsorshipBasis().name());

        return entity;
    }
}
