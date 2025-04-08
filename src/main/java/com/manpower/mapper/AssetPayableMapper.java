package com.manpower.mapper;

import com.manpower.common.Contants;
import com.manpower.model.dto.AssetPayableDTO;
import com.manpower.model.AssetPayable;

public class AssetPayableMapper {

    public static AssetPayableDTO toDTO(AssetPayable entity) {
        if (entity == null) return null;

        AssetPayableDTO dto = new AssetPayableDTO();
        dto.setId(entity.getId());
        dto.setAssetProjectId(entity.getAssetProject() != null ? entity.getAssetProject().getId() : null);
        dto.setAssetPayable(entity.getAssetPayable());
        dto.setPaymentStatus(Contants.PaymentStatusString.valueOf(entity.getPaymentStatus()));
        dto.setStatus(entity.getStatus());
        dto.setAssetId(entity.getAsset() != null ? entity.getAsset().getId() : null);
        dto.setInvoiceId(entity.getInvoice() != null ? entity.getInvoice().getId() : null);
        dto.setPaidAmount(entity.getPaidAmount());
        if(entity.getAssetProject() != null)
            dto.setAssetProjectName(entity.getAssetProject().getAssetProjectName());
        return dto;
    }

    public static AssetPayable toEntity(AssetPayableDTO dto) {
        if (dto == null) return null;

        AssetPayable entity = new AssetPayable();
        entity.setId(dto.getId());
        entity.setAssetPayable(dto.getAssetPayable());
        entity.setPaymentStatus(dto.getPaymentStatus().name());
        entity.setStatus(dto.getStatus());
        entity.setPaidAmount(dto.getPaidAmount());

        // Set associations using only ID — you'd typically fetch actual entities in service
        return entity;
    }
}
