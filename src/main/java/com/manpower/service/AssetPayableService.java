package com.manpower.service;

import com.manpower.mapper.AssetPayableMapper;
import com.manpower.model.AssetPayable;
import com.manpower.model.dto.AssetPayableDTO;
import com.manpower.repository.AssetPayableRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetPayableService {

    private final AssetPayableRepository assetPayableRepository;

    public List<AssetPayableDTO> findAll() {
        return assetPayableRepository.findAll().stream().map(AssetPayableMapper::toDTO).toList();
    }

    public AssetPayableDTO findById(Long id) {
        return assetPayableRepository.findById(id).map(AssetPayableMapper::toDTO).orElse(null);
    }

    public AssetPayable save(AssetPayableDTO assetPayableDTO) {
        AssetPayable assetPayable = AssetPayableMapper.toEntity(assetPayableDTO);
        assetPayable.setCompanyId(SecurityUtil.getCompanyClaim());
        return assetPayableRepository.save(assetPayable);
    }

    public void deleteById(Long id) {
        assetPayableRepository.deleteById(id);
    }
}
