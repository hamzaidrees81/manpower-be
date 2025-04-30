package com.manpower.pos.service;

import com.manpower.model.Company;
import com.manpower.model.User;
import com.manpower.pos.dto.SaleRequestDTO;
import com.manpower.pos.dto.SaleResponseDTO;
import com.manpower.pos.mapper.SaleMapper;
import com.manpower.pos.model.Sale;
import com.manpower.pos.repository.SaleRepository;
import com.manpower.repository.CompanyRepository;
import com.manpower.repository.UserRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final CompanyRepository companyRepository;
    private final SaleMapper saleMapper;
    private final UserRepository userRepository;

    public SaleResponseDTO createSale(SaleRequestDTO dto) {
        Integer companyId = SecurityUtil.getCompanyClaim();
        Integer userId = SecurityUtil.getUserClaim();

        Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new RuntimeException("Company not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Sale sale = saleMapper.toEntity(dto, company, user);
        sale = saleRepository.save(sale);
        return saleMapper.toResponseDTO(sale);
    }
}
