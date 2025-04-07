package com.manpower.model.dto;

import com.manpower.common.Contants;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProjectAssetSponsorshipDTO {

    private Integer id;
    private Integer sponsorId;
    private Integer assetProjectId;
    private Contants.SponsorshipType sponsorshipType;
    private BigDecimal sponsorshipValue;
    private Integer assetId;
    private Contants.SponsorshipDeterminant sponsorshipDeterminant;
    private Contants.SponsorshipBasis sponsorshipBasis;

    // Optional constructor
    public ProjectAssetSponsorshipDTO(Integer id, Integer sponsorId, Integer assetProjectId,
                                      Contants.SponsorshipType sponsorshipType, BigDecimal sponsorshipValue,
                                      Integer assetId, Contants.SponsorshipDeterminant sponsorshipDeterminant,
                                      Contants.SponsorshipBasis sponsorshipBasis) {
        this.id = id;
        this.sponsorId = sponsorId;
        this.assetProjectId = assetProjectId;
        this.sponsorshipType = sponsorshipType;
        this.sponsorshipValue = sponsorshipValue;
        this.assetId = assetId;
        this.sponsorshipDeterminant = sponsorshipDeterminant;
    }
}
