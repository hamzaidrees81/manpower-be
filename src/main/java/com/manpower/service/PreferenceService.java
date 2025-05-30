package com.manpower.service;

import com.manpower.model.Preference;
import com.manpower.pos.enums.InvoiceSequenceFormula;
import com.manpower.repository.PreferencesRepository;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PreferenceService {
  private final PreferencesRepository preferencesRepository;

  public BigDecimal findVATAmount()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(companyId).getTaxAmount();
  }

  public Integer invoiceSequence()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(companyId).getERPinvoiceSeq();
  }

  public void updateERPInvoiceNumber()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(companyId);
    preference.setERPinvoiceSeq(preference.getERPinvoiceSeq() + 1);
    preferencesRepository.save(preference);
  }

  public void updateSaleInvoiceNumber()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(companyId);
    if(preference.getSequenceFormula().equals(InvoiceSequenceFormula.YES))
        preference.setERPinvoiceSeq(preference.getERPinvoiceSeq() + 1);
    else
      preference.setSaleInvoiceSeq(preference.getSaleInvoiceSeq() + 1);

    preferencesRepository.save(preference);
  }

  public Integer assetSequence()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    return preferencesRepository.findByCompany_Id(companyId).getAssetIdSeq();
  }

  public void updateAssetNumber()
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

  public void updateUserIdNumber()
  {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(companyId);
    preference.setUserIdSeq(preference.getUserIdSeq() + 1);
    preferencesRepository.save(preference);
  }

  public Integer erpInvNo() {
    return getInvNo("ERP");
  }

  public Integer getPOSInvNO() {
    return getInvNo("");
  }

  private Integer getInvNo(String erp) {
    Integer companyId = SecurityUtil.getCompanyClaim();
    Preference preference = preferencesRepository.findByCompany_Id(companyId);
    if(preference.getSequenceFormula().equals(InvoiceSequenceFormula.YES))
      return preference.getERPinvoiceSeq();

    if(erp.equals("ERP"))
      return preference.getERPinvoiceSeq();

    return preference.getSaleInvoiceSeq();
  }


}
