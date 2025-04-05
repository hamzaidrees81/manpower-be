package com.manpower.service.impl;

import com.manpower.model.dto.ProjectAssetSponsorshipDTO;
import com.manpower.mapper.ProjectAssetSponsorshipMapper;
import com.manpower.model.ProjectAssetSponsorship;
import com.manpower.repository.ProjectAssetSponsorshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectAssetSponsorshipService {

    private final ProjectAssetSponsorshipRepository repository;

    public List<ProjectAssetSponsorshipDTO> findAll() {
        return repository.findAll()
          .stream()
          .map(ProjectAssetSponsorshipMapper::toDTO)
          .collect(Collectors.toList());
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
        ProjectAssetSponsorship saved = repository.save(entity);
        return ProjectAssetSponsorshipMapper.toDTO(saved);
    }
}
