package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.mapper.AssetMapper;
import com.manpower.model.Asset;
import com.manpower.model.Company;
import com.manpower.model.Sponsor;
import com.manpower.model.dto.AssetDTO;
import com.manpower.repository.AssetRepository;
import com.manpower.repository.CompanyRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetService {
  private final AssetRepository assetRepository;
  private final CompanyService companyService;
  private final SponsorService sponsorService;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Integer id) {
        return assetRepository.findById(id);
    }

    public AssetDTO createAsset(AssetDTO assetDTO) {
      //TODO: add logic so assets created are within allowed limit

      //assign company to asset
      Integer companyId = SecurityUtil.getCompanyClaim();
      //company must exist
      Optional<Company> company = companyService.getCompanyById(companyId);

      Sponsor sponsor = null;
      if(assetDTO.getSponsoredById() != null) {
      Optional<Sponsor> sponsorDB = sponsorService.getSponsorById(assetDTO.getSponsoredById());
      if(sponsorDB.isPresent()) {
        sponsor = sponsorDB.get();
      }
      }

      Asset asset = AssetMapper.toEntity(assetDTO, company.get(), sponsor);
      asset = assetRepository.save(asset);
      return AssetMapper.toDTO(asset);
    }


    public AssetDTO updateAsset(Integer id, AssetDTO updatedAsset) {
        Asset assetDB = assetRepository.findById(id)
                .map(asset -> {
                    asset.setName(updatedAsset.getName());
                    asset.setIdNumber(updatedAsset.getIdNumber());
                    asset.setIqamaExpiry(updatedAsset.getIqamaExpiry());
                    asset.setPhone(updatedAsset.getPhone());
                    asset.setDesignation(updatedAsset.getDesignation());
                    asset.setPassport(updatedAsset.getPassport());
                    asset.setPassportExpiry(updatedAsset.getPassportExpiry());
                    asset.setJoiningDate(updatedAsset.getJoiningDate());
                    asset.setAssetType((byte)updatedAsset.getAssetType().getValue());
                    asset.setAssetNumber(updatedAsset.getAssetNumber());
                    return assetRepository.save(asset);
                }).orElse(null);
        return AssetMapper.toDTO(assetDB);
    }

    public void deleteAsset(Integer id) {
        assetRepository.deleteById(id);
    }

  public List<Asset> getAssetByCompanyId(Integer companyId) {
      return assetRepository.findByCompany_Id(companyId);
  }
}
