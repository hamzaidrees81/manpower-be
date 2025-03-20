package com.manpower.service;

import com.manpower.model.Preference;
import com.manpower.repository.PreferencesRepository;
import com.manpower.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PreferenceService {
  PreferencesRepository preferencesRepository;

  BigDecimal findVATAmount()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(String.valueOf(companyId)).getTaxAmount();
  }

  Integer invoiceSequence()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(String.valueOf(companyId)).getInvoiceSeq();
  }

  void updateInvoiceNumber()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(String.valueOf(companyId));
    preference.setInvoiceSeq(preference.getInvoiceSeq() + 1);
    preferencesRepository.save(preference);
  }


}
