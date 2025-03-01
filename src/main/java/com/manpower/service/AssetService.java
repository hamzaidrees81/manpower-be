package com.manpower.service;

import com.manpower.model.Asset;
import com.manpower.repository.AssetRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Integer id) {
        return assetRepository.findById(id);
    }

    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset updateAsset(Integer id, Asset updatedAsset) {
        return assetRepository.findById(id)
                .map(asset -> {
                    asset.setName(updatedAsset.getName());
                    asset.setIdNumber(updatedAsset.getIdNumber());
                    asset.setIqamaExpiry(updatedAsset.getIqamaExpiry());
                    asset.setPhone(updatedAsset.getPhone());
                    asset.setDesignation(updatedAsset.getDesignation());
                    asset.setPassport(updatedAsset.getPassport());
                    asset.setPassportExpiry(updatedAsset.getPassportExpiry());
                    asset.setJoiningDate(updatedAsset.getJoiningDate());
                    asset.setAssetType(updatedAsset.getAssetType());
                    asset.setAssetNumber(updatedAsset.getAssetNumber());
                    return assetRepository.save(asset);
                }).orElse(null);
    }

    public void deleteAsset(Integer id) {
        assetRepository.deleteById(id);
    }
}
