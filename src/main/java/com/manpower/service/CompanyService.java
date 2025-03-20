package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.model.Company;
import com.manpower.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  public Optional<Company> getCompanyById(Integer id) {
    return companyRepository.findById(id);
  }

  public Company createCompany(Company company) {

    company.setStatus(Contants.StatusInt.ACTIVE.getValue());
    return companyRepository.save(company);
  }

  public Company updateCompany(Integer id, Company updatedCompany) {
    return companyRepository.findById(id)
      .map(company -> {
        company.setName(updatedCompany.getName());
        company.setAddress(updatedCompany.getAddress());
        company.setMaxAssetCount(updatedCompany.getMaxAssetCount());
        company.setHeaderImageUrl(updatedCompany.getHeaderImageUrl());
        company.setFooterImageUrl(updatedCompany.getFooterImageUrl());
        company.setBankAccountTitle(updatedCompany.getBankAccountTitle());
        company.setBankAccountNumber(updatedCompany.getBankAccountNumber());
        company.setBankIban(updatedCompany.getBankIban());
        company.setBankName(updatedCompany.getBankName());
        return companyRepository.save(company);
      }).orElse(null);
  }

  public void deleteCompany(Integer id) {
    companyRepository.findById(id).ifPresent(company -> {
      company.setStatus(Contants.StatusInt.DISABLED.getValue());
      companyRepository.save(company);
    });
  }
}