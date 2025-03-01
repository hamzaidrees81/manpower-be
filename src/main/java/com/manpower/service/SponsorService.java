package com.manpower.service;

import com.manpower.model.Sponsor;
import com.manpower.repository.SponsorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SponsorService {
    private final SponsorRepository sponsorRepository;

    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public Optional<Sponsor> getSponsorById(Integer id) {
        return sponsorRepository.findById(id);
    }

    public Sponsor createSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    public void deleteSponsor(Integer id) {
        sponsorRepository.deleteById(id);
    }

    public Sponsor updateSponsor(Integer id, Sponsor sponsor) {
        throw new RuntimeException("Not implemented yet");

    }
}
