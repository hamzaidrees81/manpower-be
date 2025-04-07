package com.manpower.mapper;

import com.manpower.common.Contants;
import com.manpower.model.*;
import com.manpower.model.dto.ProjectAssetSponsorshipDTO;

public class ProjectAssetSponsorshipMapper {

    public static ProjectAssetSponsorshipDTO toDTO(ProjectAssetSponsorship entity) {
        if (entity == null) return null;

        return new ProjectAssetSponsorshipDTO(
            entity.getId(),
            entity.getSponsor() != null ? entity.getSponsor().getId() : null,
            entity.getAssetProject() != null ? entity.getAssetProject().getId() : null,
            Contants.SponsorshipType.valueOf(entity.getSponsorshipType()),
            entity.getSponsorshipValue(),
            entity.getAsset() != null ? entity.getAsset().getId() : null,
            Contants.SponsorshipDeterminant.valueOf(entity.getSponsorshipDeterminant()),
            Contants.SponsorshipBasis.valueOf(entity.getSponsorshipBasis()),
                entity.getSponsor().getName()
        );
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
