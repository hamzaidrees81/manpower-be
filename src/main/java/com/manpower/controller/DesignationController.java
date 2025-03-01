package com.manpower.controller;

import com.manpower.model.Designation;
import com.manpower.service.DesignationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designations")
public class DesignationController {
    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    @GetMapping
    public List<Designation> getAllDesignations() {
        return designationService.getAllDesignations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Designation> getDesignationById(@PathVariable Integer id) {
        return designationService.getDesignationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Designation createDesignation(@RequestBody Designation designation) {
        return designationService.createDesignation(designation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Designation> updateDesignation(@PathVariable Integer id, @RequestBody Designation designation) {
        return ResponseEntity.ok(designationService.updateDesignation(id, designation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesignation(@PathVariable Integer id) {
        designationService.deleteDesignation(id);
        return ResponseEntity.noContent().build();
    }
}
