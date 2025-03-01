package com.manpower.service;

import com.manpower.model.AssetDesignation;
import com.manpower.repository.AssetDesignationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AssetDesignationService {
    private final AssetDesignationRepository assetDesignationRepository;

    public AssetDesignationService(AssetDesignationRepository assetDesignationRepository) {
        this.assetDesignationRepository = assetDesignationRepository;
    }

    public List<AssetDesignation> getAllAssetDesignations() {
        return assetDesignationRepository.findAll();
    }

    public Optional<AssetDesignation> getAssetDesignationById(Integer id) {
        return assetDesignationRepository.findById(id);
    }

    public AssetDesignation createAssetDesignation(AssetDesignation assetDesignation) {
        return assetDesignationRepository.save(assetDesignation);
    }

    public AssetDesignation updateAssetDesignation(Integer id, AssetDesignation assetDesignation) {
        Optional<AssetDesignation> optionalAsset = assetDesignationRepository.findById(id);
        if (optionalAsset.isPresent()) {
            AssetDesignation existingAssetDesignation = optionalAsset.get();

            existingAssetDesignation.setAsset(assetDesignation.getAsset());
            existingAssetDesignation.setDesignation(assetDesignation.getDesignation());
            return assetDesignationRepository.save(existingAssetDesignation); // Save updated entity
        } else {
            throw new RuntimeException("Asset not found with id " + id);
        }
    }
    public void deleteAssetDesignation(Integer id) {
        assetDesignationRepository.deleteById(id);
    }
}
