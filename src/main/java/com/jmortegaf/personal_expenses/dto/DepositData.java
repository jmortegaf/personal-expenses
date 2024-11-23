package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.Deposit;

public record DepositData(
        Long accountId,
        TransactionType transactionType,
        Long transactionId,
        Double depositAmount,
        String depositDateTime,
        String depositDescription) implements TransactionData{

    public DepositData(Deposit transaction) {
        this(transaction.getAccount().getId(),
                TransactionType.DEPOSIT,
                transaction.getId(),
                transaction.getDepositAmount(),
                transaction.getTransactionDateTime().toString(),
                transaction.getTransactionDescription());
    }
}
