package com.manpower.mapper;

import com.manpower.model.dto.InvoiceSponsorPayableDTO;
import com.manpower.model.InvoiceSponsorPayable;

public class InvoiceSponsorPayableMapper {

    public static InvoiceSponsorPayableDTO toDTO(InvoiceSponsorPayable entity) {
        if (entity == null) return null;

        InvoiceSponsorPayableDTO dto = new InvoiceSponsorPayableDTO();
        dto.setId(entity.getId());
        dto.setProjectSponsorshipId(entity.getProjectSponsorshipId() != null ? entity.getProjectSponsorshipId().getId() : null);
        dto.setSponsorshipPayable(entity.getSponsorshipPayable());
        dto.setPaymentStatus(entity.getPaymentStatus());
        dto.setStatus(entity.getStatus());
        dto.setSponsorshipAssetId(entity.getSponsorshipAsset() != null ? entity.getSponsorshipAsset().getId() : null);
        dto.setSponsorshipDeterminant(entity.getSponsorshipDeterminant());
        dto.setSponsorId(entity.getSponsor() != null ? entity.getSponsor().getId() : null);
        dto.setInvoiceId(entity.getInvoice() != null ? entity.getInvoice().getId() : null);
        dto.setPaidAmount(entity.getPaidAmount());
        return dto;
    }

    public static InvoiceSponsorPayable toEntity(InvoiceSponsorPayableDTO dto) {
        if (dto == null) return null;

        InvoiceSponsorPayable entity = new InvoiceSponsorPayable();
        entity.setId(dto.getId());
        entity.setSponsorshipPayable(dto.getSponsorshipPayable());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setStatus(dto.getStatus());
        entity.setSponsorshipDeterminant(dto.getSponsorshipDeterminant());
        entity.setPaidAmount(dto.getPaidAmount());

        // Set associations using only ID — should attach real entities in service layer
        return entity;
    }
}
