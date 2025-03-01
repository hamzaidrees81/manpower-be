package com.manpower.controller;

import com.manpower.model.Sponsor;
import com.manpower.service.SponsorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorController {
    private final SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public List<Sponsor> getAllSponsors() {
        return sponsorService.getAllSponsors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable Integer id) {
        return sponsorService.getSponsorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sponsor createSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.createSponsor(sponsor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable Integer id, @RequestBody Sponsor sponsor) {
        return ResponseEntity.ok(sponsorService.updateSponsor(id, sponsor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Integer id) {
        sponsorService.deleteSponsor(id);
        return ResponseEntity.noContent().build();
    }
}
