package com.manpower.mapper;

import com.manpower.model.Project;
import com.manpower.model.Company;
import com.manpower.model.Client;
import com.manpower.model.dto.ProjectDTO;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project entity) {
        if (entity == null) return null;

        return ProjectDTO.builder()
                .id(entity.getId())
                .companyId(entity.getCompany() != null ? entity.getCompany().getId() : null)
                .clientId(entity.getClient() != null ? entity.getClient().getId() : null)
                .projectId(entity.getProjectId())
                .name(entity.getName())
                .location(entity.getLocation())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }

    public static Project toEntity(ProjectDTO dto) {
        if (dto == null) return null;

        Project project = new Project();
        project.setId(dto.getId());

        if (dto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(dto.getCompanyId());
            project.setCompany(company);
        }

        if (dto.getClientId() != null) {
            Client client = new Client();
            client.setId(dto.getClientId());
            project.setClient(client);
        }

        project.setProjectId(dto.getProjectId());
        project.setName(dto.getName());
        project.setLocation(dto.getLocation());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());

        return project;
    }
}
