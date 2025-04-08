package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.mapper.AssetPayableMapper;
import com.manpower.model.AssetPayable;
import com.manpower.model.dto.AssetPayableDTO;
import com.manpower.repository.AssetPayableRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetPayableService {

    private final AssetPayableRepository assetPayableRepository;

    public List<AssetPayableDTO> findAll() {
        return assetPayableRepository.findByCompanyId(SecurityUtil.getCompanyClaim()).stream().map(AssetPayableMapper::toDTO).toList();
    }

    public List<AssetPayableDTO> findPayablesByAssetId(Integer assetId) {
        return assetPayableRepository.findByAssetIdAndCompanyId(assetId, SecurityUtil.getCompanyClaim()).stream().map(AssetPayableMapper::toDTO).toList();
    }

    public List<AssetPayableDTO> findPayablesByAssetIdAndStatus(Integer assetId, Contants.PaymentStatusString paymentStatusString) {
        return assetPayableRepository.findByAssetIdAndCompanyIdAndPaymentStatus(assetId, SecurityUtil.getCompanyClaim(), paymentStatusString.name()).stream().map(AssetPayableMapper::toDTO).toList();
    }

    public List<AssetPayableDTO> findPayablesByStatus(Contants.PaymentStatusString paymentStatusString) {
        return assetPayableRepository.findByCompanyIdAndPaymentStatus(SecurityUtil.getCompanyClaim(), paymentStatusString.name()).stream().map(AssetPayableMapper::toDTO).toList();
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

    public List<AssetPayableDTO> findPayables(Integer id, Contants.PaymentStatusString paymentStatus) {
        if (id != null && paymentStatus != Contants.PaymentStatusString.ALL) {
            return findPayablesByAssetIdAndStatus(id, paymentStatus); // method for both
        } else if (id != null) {
            return findPayablesByAssetId(id); // method for asset ID only
        } else if (paymentStatus != Contants.PaymentStatusString.ALL) {
            return findPayablesByStatus(paymentStatus); // method for status only
        } else {
            return findAll(); // method for no parameters
        }
    }
}
