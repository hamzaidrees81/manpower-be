package com.manpower.service;

import com.manpower.mapper.ProjectMapper;
import com.manpower.model.Client;
import com.manpower.model.Project;
import com.manpower.model.dto.ProjectDTO;
import com.manpower.repository.AssetProjectRepository;
import com.manpower.repository.ProjectRepository;
import com.manpower.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
  private final AssetProjectRepository assetProjectRepository;
    private final CompanyService companyService;

    public ProjectService(ProjectRepository projectRepository, AssetProjectRepository assetProjectRepository, CompanyService companyService) {
        this.projectRepository = projectRepository;
    this.assetProjectRepository = assetProjectRepository;
        this.companyService = companyService;
    }

    public List<Project> getAllProjectsByCompany() {
      Integer companyId = SecurityUtil.getCompanyClaim();
      return projectRepository.findProjectByCompany_Id(companyId);
    }

    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        projectDTO.setCompanyId(SecurityUtil.getCompanyClaim());
        Project project = ProjectMapper.toEntity(projectDTO);
//      project.setCompany(companyService.getCompanyById(SecurityUtil.getCompanyClaim()).get());
        project = projectRepository.save(project);
        return ProjectMapper.toDTO(project);
    }

    public ProjectDTO updateProject(Integer id, ProjectDTO updatedProject) {
        Project project1 = projectRepository.findById(id)
                .map(project -> {
                    project.setName(updatedProject.getName());
                    project.setLocation(updatedProject.getLocation());
                    project.setStartDate(updatedProject.getStartDate());
                    project.setEndDate(updatedProject.getEndDate());
                    return projectRepository.save(project);
                }).orElse(null);

        return ProjectMapper.toDTO(project1);
    }



    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

  public List<Project> findProjectByClient(Client client) {
    return projectRepository.findProjectByClient(client);
  }
}
