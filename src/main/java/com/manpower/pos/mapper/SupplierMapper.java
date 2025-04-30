package com.manpower.pos.mapper;

import com.manpower.pos.dto.SupplierDTO;
import com.manpower.pos.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public SupplierDTO toDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setAddress(supplier.getAddress());
        dto.setContact(supplier.getContact());
        dto.setComments(supplier.getComments());
        return dto;
    }

    public Supplier toEntity(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setAddress(dto.getAddress());
        supplier.setContact(dto.getContact());
        supplier.setComments(dto.getComments());
        return supplier;
    }
}
