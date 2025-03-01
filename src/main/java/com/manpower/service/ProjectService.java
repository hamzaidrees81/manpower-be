package com.manpower.service;

import com.manpower.model.Project;
import com.manpower.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Integer id, Project updatedProject) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(updatedProject.getName());
                    project.setLocation(updatedProject.getLocation());
                    project.setStartDate(updatedProject.getStartDate());
                    project.setEndDate(updatedProject.getEndDate());
                    return projectRepository.save(project);
                }).orElse(null);
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}
