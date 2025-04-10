package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.mapper.AssetMapper;
import com.manpower.mapper.AssetProjectMapper;
import com.manpower.model.*;
import com.manpower.model.dto.AssetDTO;
import com.manpower.model.dto.AssetProjectDTO;
import com.manpower.repository.AssetProjectRepository;
import com.manpower.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetProjectService {
    private final AssetProjectRepository assetProjectRepository;
    private final CompanyService companyService;
    private final ProjectService projectService;
    private final DesignationService designationService;
    private final AssetService assetService;

    public AssetProjectService(AssetProjectRepository assetProjectRepository, CompanyService companyService, ProjectService projectService, DesignationService designationService, AssetService assetService) {
        this.assetProjectRepository = assetProjectRepository;
        this.companyService = companyService;
        this.projectService = projectService;
        this.designationService = designationService;
        this.assetService = assetService;
    }

    public List<AssetProject> getAllAssetProjects() {
        return assetProjectRepository.findAll();
    }

    public Optional<AssetProject> getAssetProjectById(Integer id) {
        return assetProjectRepository.findById(id);
    }

    public AssetProjectDTO createAssetProject(AssetProjectDTO assetProjectDTO) {
        //map to db object
        Integer companyId = SecurityUtil.getCompanyClaim();
        Integer assetId = assetProjectDTO.getAssetId();
        Integer projectId = assetProjectDTO.getProjectId();
        Integer designationId = assetProjectDTO.getDesignationId();

        //company must exist
        Company company = null;
        Asset asset = null;
        Project project = null;
        Designation designation = null;

        Optional<Company> companyDB = companyService.getCompanyById(companyId);
        Optional<Project> projectDB = projectService.getProjectById(projectId);
        Optional<Asset> assetDB = assetService.getAssetById(assetId);
        Optional<Designation> designationDB = designationService.getDesignationById(designationId);

        if(companyDB.isEmpty())
        {
            throw new RuntimeException("Asset not found");
        }
        if(assetDB.isEmpty())
        {
            throw new RuntimeException("Asset not found");
        }
        if(projectDB.isEmpty()) {
            throw new RuntimeException("Project not found");
        }
        if( designationDB.isEmpty())
        {
            throw new RuntimeException("Designation not found");
        }

        company = companyDB.get();
        asset = assetDB.get();
        project = projectDB.get();
        designation = designationDB.get();

        AssetProject assetProject = AssetProjectMapper.toEntity(assetProjectDTO, company, asset,project,designation);
        assetProject.setStatus(Contants.Status.ACTIVE.getValue());
        return AssetProjectMapper.toDTO(assetProjectRepository.save(assetProject));
    }


    public AssetProjectDTO updateAssetProject(Integer id, AssetProjectDTO dto) {
        Optional<AssetProject> assetProjectOptional = assetProjectRepository.findById(id)
          .map(assetProject -> {
              // Update fields if the new values are not null
              assetService.getAssetById(dto.getAssetId()).ifPresent(assetProject::setAsset);
              projectService.getProjectById(dto.getProjectId()).ifPresent(assetProject::setProject);
              designationService.getDesignationById(dto.getDesignationId()).ifPresent(assetProject::setDesignation);

              if (dto.getAssetProjectName() != null) {
                  assetProject.setAssetProjectName(dto.getAssetProjectName());
              }
              if (dto.getRegularRate() != null) {
                  assetProject.setRegularRate(dto.getRegularRate());
              }
              if (dto.getOvertimeRate() != null) {
                  assetProject.setOvertimeRate(dto.getOvertimeRate());
              }
              if (dto.getRegularRatePaid() != null) {
                  assetProject.setRegularRatePaid(dto.getRegularRatePaid());
              }
              if (dto.getOvertimeRatePaid() != null) {
                  assetProject.setOvertimeRatePaid(dto.getOvertimeRatePaid());
              }
              if (dto.getStartDate() != null) {
                  assetProject.setStartDate(dto.getStartDate());
              }
              assetProject.setEndDate(dto.getEndDate()); // Can be null if removed
              if (dto.getIsActive() != null) {
                  assetProject.setIsActive(dto.getIsActive().getValue());
              }
              if (dto.getStatus() != null) {
                  assetProject.setStatus(dto.getStatus().getValue());
              }

              return assetProjectRepository.save(assetProject);
          });

        if(assetProjectOptional.isEmpty()) {
            throw new RuntimeException("AssetProject not found");
        }
        return AssetProjectMapper.toDTO(assetProjectOptional.get());

    }

    public void deleteAssetProject(Integer id) {
        assetProjectRepository.deleteById(id);
    }


    public List<AssetProject> getProjectsByAssetBetweenDate(Integer assetId, LocalDate startDate, LocalDate endDate) {
        return assetProjectRepository.findProjectsByAsset_IdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(assetId, startDate, endDate);
    }

    public List<AssetProject> findProjectByClientAndDate(Client client, LocalDate endDate) {
        return assetProjectRepository.findAssetsByClientAndProjectEndDate(client, endDate);
    }

  public List<AssetProjectDTO> getAssetProjectByAssetId(Integer assetId, Contants.AssetProjectStatus assetProjectStatus) {
    return assetProjectRepository.findProjectsByAsset_IdAndIsActive(assetId, assetProjectStatus.getValue()).stream().map(AssetProjectMapper::toDTO).collect(Collectors.toList());
  }
  public Long countAssetsByProjectId(Integer projectId) {
    return assetProjectRepository.countAssetsByProjectId(projectId);
  }

  public List<AssetDTO> getAssetsByProjectId(Integer projectId) {

      List<Asset> assetList = assetProjectRepository.findAssetsByProjectId(projectId);
      return assetList.stream().map(AssetMapper::toDTO).collect(Collectors.toList());
  }

  public Long countAssetsWithActiveProjectsActive()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return assetProjectRepository.countAssetsWithActiveProjects(companyId);
  }

  public Long countAssetsWithNoActiveProjects()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return assetProjectRepository.countAssetsWithNoActiveProjects(companyId);
  }

  public List<AssetDTO> findAssetsWithActiveProjects()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    List<Asset> assetList = assetProjectRepository.findAssetsWithActiveProjects(companyId);
    return assetList.stream().map(AssetMapper::toDTO).toList();
  }

  public List<AssetDTO> findAssetsWithNoActiveProjects()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    List<Asset> assetList = assetProjectRepository.findAssetsWithNoActiveProjects(companyId);
    return assetList.stream().map(AssetMapper::toDTO).toList();
  }
}
