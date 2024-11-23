package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.CreditPayment;

public record CreditPaymentData(
        Long accountId,
        TransactionType transactionType,
        Long transactionId,
        Double paymentAmount,
        String paymentDateTime,
        String paymentDescription) implements TransactionData{

    public CreditPaymentData(CreditPayment creditPayment) {
        this(creditPayment.getAccount().getId(),TransactionType.PAYMENT,creditPayment.getId(),
                creditPayment.getDepositAmount(),creditPayment.getTransactionDateTime().toString(),
                creditPayment.getTransactionDescription());
    }

}
