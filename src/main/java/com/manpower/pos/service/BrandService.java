package com.manpower.pos.service;

import com.manpower.model.Company;
import com.manpower.pos.dto.BrandDto;
import com.manpower.pos.mapper.BrandMapper;
import com.manpower.pos.model.Brand;
import com.manpower.pos.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandDto save(BrandDto brandDto) {
        Brand brand = brandMapper.toEntity(brandDto);

        // Set the company from SecurityUtil
        Company company = new Company();
        company.setId(com.manpower.util.SecurityUtil.getCompanyClaim());
        brand.setCompany(company);

        brand = brandRepository.save(brand);
        return brandMapper.toDto(brand);
    }

    public List<BrandDto> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
    }

    public BrandDto findById(Integer id) {
        return brandRepository.findById(Long.valueOf(id))
                .map(brandMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    public void deleteById(Integer id) {
        brandRepository.deleteById(Long.valueOf(id));
    }
}
