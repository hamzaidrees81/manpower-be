package com.manpower.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class LedgerDTO {
    BigDecimal totalIncome; //via invoice
    BigDecimal totalExpense;
    BigDecimal totalCompanyExpenses;
    BigDecimal totalAssetExpenses; //total expense
    BigDecimal totalPaidToAssets; //total paid to assets
    BigDecimal totalPaidToSponsors;
    BigDecimal profit;
    List<PaymentDTO> payments;
}
