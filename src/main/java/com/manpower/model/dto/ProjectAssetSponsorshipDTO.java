package com.manpower.model.dto;

import com.manpower.common.Contants;
import com.manpower.model.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectAssetSponsorshipDTO {

    private Integer id;
    private Integer sponsorId;
    private Integer assetId;
    private String assetName;
    private Integer assetProjectId;
    private String assetProjectName;
    private String sponsorName;
    private SponsorDTO sponsor;
    private Contants.SponsorshipType sponsorshipType;
    private BigDecimal sponsorshipValue;
    private Contants.SponsorshipDeterminant sponsorshipDeterminant;
    private Contants.SponsorshipBasis sponsorshipBasis;
    private ProjectDTO project;
}
