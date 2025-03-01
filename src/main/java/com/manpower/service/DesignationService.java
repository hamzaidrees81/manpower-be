package com.manpower.service;

import com.manpower.model.Designation;
import com.manpower.repository.DesignationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DesignationService {
    private final DesignationRepository designationRepository;

    public DesignationService(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    public Optional<Designation> getDesignationById(Integer id) {
        return designationRepository.findById(id);
    }

    public Designation createDesignation(Designation designation) {
        return designationRepository.save(designation);
    }

    public void deleteDesignation(Integer id) {
        designationRepository.deleteById(id);
    }

    public Designation updateDesignation(Integer id, Designation designation) {
        throw new RuntimeException("Not implemented yet");
    }
}
