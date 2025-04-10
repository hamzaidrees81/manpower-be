package com.manpower.service;

import com.manpower.model.Sponsor;
import com.manpower.repository.SponsorRepository;
import com.manpower.util.SecurityUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SponsorService {
    private final SponsorRepository sponsorRepository;
    private final CompanyService companyService;

    public SponsorService(SponsorRepository sponsorRepository, CompanyService companyService) {
        this.sponsorRepository = sponsorRepository;
        this.companyService = companyService;
    }

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public Optional<Sponsor> getSponsorById(Integer id) {
        return sponsorRepository.findById(id);
    }

    public Sponsor createSponsor(Sponsor sponsor) { //TODO: MAKE DTO
        sponsor.setCompany(companyService.getCompanyById(SecurityUtil.getCompanyClaim()).get());
        return sponsorRepository.save(sponsor);
    }

    public void deleteSponsor(Integer id) {
        sponsorRepository.deleteById(id);
    }

    public Sponsor updateSponsor(Integer id, Sponsor sponsor) {
        throw new RuntimeException("Not implemented yet");

    }
}
