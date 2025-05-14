package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.mapper.InvoiceSponsorPayableMapper;
import com.manpower.model.InvoiceSponsorPayable;
import com.manpower.model.dto.InvoiceSponsorPayableDTO;
import com.manpower.model.dto.InvoiceSponsorPayableDTOWithStats;
import com.manpower.repository.InvoiceSponsorPayableRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceSponsorPayableService {

    private final InvoiceSponsorPayableRepository invoiceSponsorPayableRepository;

    public List<InvoiceSponsorPayableDTO> findAll() {
        return invoiceSponsorPayableRepository.findAll().stream().map(InvoiceSponsorPayableMapper::toDTO).collect(Collectors.toList());
    }

    public InvoiceSponsorPayableDTO findById(Long id) {
        return invoiceSponsorPayableRepository.findById(id).map(InvoiceSponsorPayableMapper::toDTO).orElse(null);
    }

    public InvoiceSponsorPayable save(InvoiceSponsorPayableDTO sponsorPayableDTO) {
        InvoiceSponsorPayable invoiceSponsorPayable = InvoiceSponsorPayableMapper.toEntity(sponsorPayableDTO);
        invoiceSponsorPayable.setCompanyId(SecurityUtil.getCompanyClaim());
        return invoiceSponsorPayableRepository.save(invoiceSponsorPayable);
    }

    public void deleteById(Long id) {
        invoiceSponsorPayableRepository.deleteById(id);
    }

    public InvoiceSponsorPayableDTOWithStats findPayablesWithStats(Integer id, Contants.PaymentStatusString paymentStatus) {

        List<InvoiceSponsorPayableDTO> payables = findPayables(id, paymentStatus);
        BigDecimal totalPayable = BigDecimal.ZERO;
        BigDecimal totalPaidAmount = BigDecimal.ZERO;
        BigDecimal pendingAmount;

        //calculate total payable
        for(InvoiceSponsorPayableDTO payable : payables) {
            totalPayable = totalPayable.add(payable.getSponsorshipPayable());
            totalPaidAmount = totalPaidAmount.add(payable.getPaidAmount());
        }

        pendingAmount = totalPayable.subtract(totalPaidAmount);

        return InvoiceSponsorPayableDTOWithStats.builder()
                .totalAmount(totalPayable)
                .paidAmount(totalPaidAmount)
                .pendingAmount(pendingAmount)
                .payables(payables)
                .build();
    }

    public List<InvoiceSponsorPayableDTO> findPayables(Integer sponsorId, Contants.PaymentStatusString paymentStatus) {
        if (sponsorId != null && paymentStatus != Contants.PaymentStatusString.ALL) {
            return findPayablesBySponsorIdAndStatus(sponsorId, paymentStatus); // method for both
        } else if (sponsorId != null) {
            return findPayablesBySponsorId(sponsorId); // method for asset ID only
        } else if (paymentStatus != Contants.PaymentStatusString.ALL) {
            return findPayablesByStatus(paymentStatus); // method for status only
        } else {
            return findAll(); // method for no parameters
        }
    }


    public List<InvoiceSponsorPayable> findPayablesByAssetId(Integer assetId) {
        return invoiceSponsorPayableRepository.findBySponsorshipAsset_Id(assetId);
    }


    public List<InvoiceSponsorPayableDTO> findPayablesBySponsorId(Integer sponsorId) {
        return invoiceSponsorPayableRepository.findBySponsorIdAndCompanyId(sponsorId, SecurityUtil.getCompanyClaim()).stream().map(InvoiceSponsorPayableMapper::toDTO).toList();
    }

    public List<InvoiceSponsorPayableDTO> findPayablesBySponsorIdAndStatus(Integer sponsorId, Contants.PaymentStatusString paymentStatusString) {
        return invoiceSponsorPayableRepository.findBySponsorIdAndCompanyIdAndPaymentStatus(sponsorId, SecurityUtil.getCompanyClaim(), paymentStatusString.name()).stream().map(InvoiceSponsorPayableMapper::toDTO).toList();
    }

    public List<InvoiceSponsorPayableDTO> findPayablesByStatus(Contants.PaymentStatusString paymentStatusString) {
        return invoiceSponsorPayableRepository.findByCompanyIdAndPaymentStatus(SecurityUtil.getCompanyClaim(), paymentStatusString.name()).stream().map(InvoiceSponsorPayableMapper::toDTO).toList();

    }
}