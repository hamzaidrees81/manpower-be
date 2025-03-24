package com.manpower.mapper;

import com.manpower.common.Contants;
import com.manpower.model.Asset;
import com.manpower.model.Company;
import com.manpower.model.Sponsor;
import com.manpower.model.dto.AssetDTO;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    public static AssetDTO toDTO(Asset asset) {
        if (asset == null) {
            return null;
        }

        return AssetDTO.builder()
                .id(asset.getId())
                .sponsoredById(asset.getSponsoredBy() != null ? asset.getSponsoredBy().getId() : null)
                .sponsoredName(asset.getSponsoredBy() != null ? asset.getSponsoredBy().getName() : null)
                .name(asset.getName())
                .idNumber(asset.getIdNumber())
                .iqamaExpiry(asset.getIqamaExpiry())
                .phone(asset.getPhone())
                .designation(asset.getDesignation())
                .passport(asset.getPassport())
                .passportExpiry(asset.getPassportExpiry())
                .joiningDate(asset.getJoiningDate())
                .assetType(Contants.AssetType.fromValue(asset.getAssetType()))
                .assetNumber(asset.getAssetNumber())
                .assetOwnership(Contants.AssetOwnership.fromValue(asset.getAssetOwnership()))
                .build();
    }

    public static  Asset toEntity(AssetDTO assetDTO, Company company, Sponsor sponsor) {
        if (assetDTO == null) {
            return null;
        }

        Asset asset = new Asset();
        asset.setId(assetDTO.getId());
        // Assuming you have a way to fetch the company and sponsor based on their IDs
        // This should be done through your service layer where you fetch those entities by their ID
        asset.setCompany(company);
        asset.setSponsoredBy(sponsor);
        asset.setName(assetDTO.getName());
        asset.setIdNumber(assetDTO.getIdNumber());
        asset.setIqamaExpiry(assetDTO.getIqamaExpiry());
        asset.setPhone(assetDTO.getPhone());
        asset.setDesignation(assetDTO.getDesignation());
        asset.setPassport(assetDTO.getPassport());
        asset.setPassportExpiry(assetDTO.getPassportExpiry());
        asset.setJoiningDate(assetDTO.getJoiningDate());
        asset.setAssetType((byte)assetDTO.getAssetType().getValue());
        asset.setAssetNumber(assetDTO.getAssetNumber());
        asset.setAssetOwnership((byte) assetDTO.getAssetOwnership().getValue());
        asset.setSponsorshipPercentage(assetDTO.getSponsoredPercentage());

        return asset;
    }
}
