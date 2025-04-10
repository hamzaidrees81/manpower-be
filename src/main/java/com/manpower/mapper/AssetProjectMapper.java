package com.manpower.mapper;

import com.manpower.common.Contants;
import com.manpower.model.*;
import com.manpower.model.dto.AssetProjectDTO;

public class AssetProjectMapper {

    public static AssetProjectDTO toDTO(AssetProject assetProject) {
        return AssetProjectDTO.builder()
                .id(assetProject.getId())
                .assetId(assetProject.getAsset().getId())  // Mapping the Asset ID
                .projectId(assetProject.getProject().getId())  // Mapping the Project ID
                .projectName(assetProject.getProject().getName())  // Assuming Project has a 'name' field
                .designationName(assetProject.getDesignation().getName())  // Assuming Designation has a 'name' field
                .designationId(assetProject.getDesignation().getId())
                .assetProjectName(assetProject.getAssetProjectName())
                .regularRate(assetProject.getRegularRate())
                .overtimeRate(assetProject.getOvertimeRate())
                .regularRatePaid(assetProject.getRegularRatePaid())
                .overtimeRatePaid(assetProject.getOvertimeRatePaid())
                .startDate(assetProject.getStartDate())
                .endDate(assetProject.getEndDate())
                .isActive(Contants.AssetProjectStatus.fromValue(assetProject.getIsActive()))
                .status(Contants.Status.fromValue(assetProject.getStatus()))
                .build();
    }

    // Optional: You can add a reverse method if needed, e.g., for converting DTO to entity
    public static AssetProject toEntity(AssetProjectDTO assetProjectDTO, Company company, Asset asset, Project project, Designation designation) {
        AssetProject assetProject = new AssetProject();
        assetProject.setId(assetProjectDTO.getId());
        assetProject.setCompany(company); // You'll need to set the actual Company object here
        assetProject.setAsset(asset); // Similarly, set the actual Asset object here
        assetProject.setProject(project); // Set the actual Project object
        assetProject.setDesignation(designation); // Set the actual Designation object
        assetProject.setAssetProjectName(assetProjectDTO.getAssetProjectName());
        assetProject.setRegularRate(assetProjectDTO.getRegularRate());
        assetProject.setOvertimeRate(assetProjectDTO.getOvertimeRate());
        assetProject.setRegularRatePaid(assetProjectDTO.getRegularRatePaid());
        assetProject.setOvertimeRatePaid(assetProjectDTO.getOvertimeRatePaid());
        assetProject.setStartDate(assetProjectDTO.getStartDate());
        assetProject.setEndDate(assetProjectDTO.getEndDate());
        assetProject.setIsActive(assetProjectDTO.getIsActive().getValue());
        assetProject.setStatus(assetProjectDTO.getStatus()!=null ? assetProjectDTO.getStatus().getValue() : Contants.Status.ACTIVE.getValue()); //TODO: WHAT SHOULD BE DEFAULT
        return assetProject;
    }
}
