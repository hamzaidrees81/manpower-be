package com.manpower.service;

import com.manpower.model.Preference;
import com.manpower.repository.PreferencesRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PreferenceService {
  private final PreferencesRepository preferencesRepository;

  BigDecimal findVATAmount()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(companyId).getTaxAmount();
  }

  Integer invoiceSequence()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(companyId).getInvoiceSeq();
  }

  void updateInvoiceNumber()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(companyId);
    preference.setInvoiceSeq(preference.getInvoiceSeq() + 1);
    preferencesRepository.save(preference);
  }

  Integer assetSequence()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(companyId).getAssetIdSeq();
  }

  void updateAssetNumber()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(companyId);
    preference.setAssetIdSeq(preference.getAssetIdSeq() + 1);
    preferencesRepository.save(preference);
  }

  Integer userIdSequence()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(companyId).getUserIdSeq();
  }

  void updateUserIdNumber()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(companyId);
    preference.setUserIdSeq(preference.getUserIdSeq() + 1);
    preferencesRepository.save(preference);
  }

}
