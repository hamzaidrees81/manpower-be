package com.manpower.service;

import com.manpower.mapper.InvoiceSponsorPayableMapper;
import com.manpower.model.InvoiceSponsorPayable;
import com.manpower.model.dto.InvoiceSponsorPayableDTO;
import com.manpower.repository.InvoiceSponsorPayableRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
