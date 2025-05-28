package com.manpower.mapper;

import com.manpower.model.Company;
import com.manpower.model.dto.CompanyDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    // Manual mapping method
    public static CompanyDTO toDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .maxAssetCount(company.getMaxAssetCount())
                .headerImageUrl(company.getHeaderImageUrl())
                .footerImageUrl(company.getFooterImageUrl())
                .bankAccountTitle(company.getBankAccountTitle())
                .bankAccountNumber(company.getBankAccountNumber())
                .bankIban(company.getBankIban())
                .bankName(company.getBankName())
                .VAT(company.getVat())
                .logoImageUrl(company.getLogoImageUrl())
                .build();
    }

    // Manual mapping for a list of companies (optional)
    public static List<CompanyDTO> toDTOList(List<Company> companies) {
        return companies.stream()
                .map(CompanyMapper::toDTO)
                .collect(Collectors.toList());
    }
}
