package com.manpower.service;

import com.manpower.model.AssetProject;
import com.manpower.repository.AssetProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssetProjectService {
    private final AssetProjectRepository assetProjectRepository;

    public AssetProjectService(AssetProjectRepository assetProjectRepository) {
        this.assetProjectRepository = assetProjectRepository;
    }

    public List<AssetProject> getAllAssetProjects() {
        return assetProjectRepository.findAll();
    }

    public Optional<AssetProject> getAssetProjectById(Integer id) {
        return assetProjectRepository.findById(id);
    }

    public AssetProject createAssetProject(AssetProject assetProject) {
        return assetProjectRepository.save(assetProject);
    }

    public void deleteAssetProject(Integer id) {
        assetProjectRepository.deleteById(id);
    }

    public AssetProject updateAssetProject(Integer id, AssetProject assetProject) {
            throw new RuntimeException("Not implemented yet");
    }

    public List<AssetProject> getProjectsByAssetBetweenDate(Integer assetId, LocalDate startDate, LocalDate endDate) {
        return assetProjectRepository.findProjectsByAsset_IdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(assetId, startDate, endDate);
    }
}
