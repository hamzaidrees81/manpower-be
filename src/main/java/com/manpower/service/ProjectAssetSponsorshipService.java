package com.manpower.service;

import com.manpower.mapper.ProjectAssetSponsorshipMapper;
import com.manpower.model.Asset;
import com.manpower.model.AssetProject;
import com.manpower.model.ProjectAssetSponsorship;
import com.manpower.model.Sponsor;
import com.manpower.model.dto.ProjectAssetSponsorshipDTO;
import com.manpower.repository.AssetProjectRepository;
import com.manpower.repository.AssetRepository;
import com.manpower.repository.ProjectAssetSponsorshipRepository;
import com.manpower.repository.SponsorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectAssetSponsorshipService {

    private final ProjectAssetSponsorshipRepository repository;
    private final SponsorRepository sponsorRepository;
    private final AssetProjectService assetProjectService;
    private final AssetProjectRepository assetProjectRepository;
    private final AssetRepository assetRepository;

    public List<ProjectAssetSponsorshipDTO> findAll() {
        return repository.findAll()
          .stream()
          .map(ProjectAssetSponsorshipMapper::toDTO)
          .collect(Collectors.toList());
    }

    public List<ProjectAssetSponsorshipDTO> findAllByAssetId(Integer id) {
        return repository.findByAsset_Id(id).stream()
                .map(ProjectAssetSponsorshipMapper::toDTO).toList();
    }

    public List<ProjectAssetSponsorshipDTO> findAllByAssetProjectId(Integer id) {
        return repository.findByAssetProject_Id(id).stream()
                .map(ProjectAssetSponsorshipMapper::toDTO).toList();
    }

    public Optional<ProjectAssetSponsorshipDTO> findById(Long id) {
        return repository.findById(id)
          .map(ProjectAssetSponsorshipMapper::toDTO);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public ProjectAssetSponsorshipDTO save(ProjectAssetSponsorshipDTO dto) {
        ProjectAssetSponsorship entity = ProjectAssetSponsorshipMapper.toEntity(dto);

        //create associations
        Sponsor sponsor = sponsorRepository.findById(dto.getSponsorId()).orElse(null);
        AssetProject assetProject = assetProjectRepository.findById(dto.getAssetProjectId()).orElse(null);
        Asset asset = assetRepository.findById(dto.getAssetId()).orElse(null);

        entity.setAsset(asset);
        entity.setSponsor(sponsor);
        entity.setAssetProject(assetProject);

        ProjectAssetSponsorship saved = repository.save(entity);
        return ProjectAssetSponsorshipMapper.toDTO(saved);
    }
}
