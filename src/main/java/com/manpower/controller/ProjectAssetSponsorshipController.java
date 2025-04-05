package com.manpower.controller;

import com.manpower.model.dto.ProjectAssetSponsorshipDTO;
import com.manpower.service.impl.ProjectAssetSponsorshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/project-asset-sponsorships")
@RequiredArgsConstructor
public class ProjectAssetSponsorshipController {

    private final ProjectAssetSponsorshipService sponsorshipService;

    @GetMapping
    public ResponseEntity<List<ProjectAssetSponsorshipDTO>> getAllSponsorships() {
        List<ProjectAssetSponsorshipDTO> dtos = new ArrayList<>(sponsorshipService.findAll());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectAssetSponsorshipDTO> getSponsorshipById(@PathVariable Long id) {
        return sponsorshipService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsorship(@PathVariable Long id) {
        if (sponsorshipService.existsById(id)) {
            sponsorshipService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProjectAssetSponsorshipDTO> createSponsorship(@RequestBody ProjectAssetSponsorshipDTO dto) {
        ProjectAssetSponsorshipDTO created = sponsorshipService.save(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectAssetSponsorshipDTO> updateSponsorship(@PathVariable Integer id,
                                                                        @RequestBody ProjectAssetSponsorshipDTO dto) {
        if (!sponsorshipService.existsById(Long.valueOf(id))) {
            return ResponseEntity.notFound().build();
        }

        dto.setId(id); // ensure ID is set for update
        ProjectAssetSponsorshipDTO updated = sponsorshipService.save(dto);
        return ResponseEntity.ok(updated);
    }

}
