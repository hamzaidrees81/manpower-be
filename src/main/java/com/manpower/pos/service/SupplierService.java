package com.manpower.pos.service;

import com.manpower.pos.dto.SupplierDTO;
import com.manpower.pos.mapper.SupplierMapper;
import com.manpower.pos.model.Supplier;
import com.manpower.pos.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierDTO save(SupplierDTO dto) {
        Supplier supplier = supplierMapper.toEntity(dto);
        return supplierMapper.toDTO(supplierRepository.save(supplier));
    }

    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll().stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SupplierDTO findById(Integer id) {
        return supplierRepository.findById(Long.valueOf(id))
                .map(supplierMapper::toDTO)
                .orElse(null);
    }

    public void delete(Integer id) {
        supplierRepository.deleteById(Long.valueOf(id));
    }
}
