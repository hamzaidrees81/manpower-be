package com.manpower.common;

public class PaymentConstant {
    public enum PaymentMethod {
        BANK_TRANSFER,
        CASH,
        CHEQUE,
        ONLINE_TRANSFER,
        CREDIT_CARD,
        DEBIT_CARD,
        MOBILE_WALLET,
        OTHER
    }

    public enum PaymentStatus {
        COMPLETED,
        PENDING,
        FAILED
    }

    public enum PaidToType {
        ASSET,
        SPONSOR,
        EXPENSE,
        INVOICE,
        OTHER //other and invoice means money is incoming, other 3 are outgoing
    }

    public enum PaymentType {
        INITIAL,
        ADJUSTMENT,
        FULL,
        REFUND,
        ADVANCE
    }


}
