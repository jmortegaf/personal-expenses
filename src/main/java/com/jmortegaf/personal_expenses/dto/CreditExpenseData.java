package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.CreditExpense;

public record CreditExpenseData(
        Long accountId,
        TransactionType transactionType,
        Long transactionId,
        Integer paymentsNumber,
        Double paymentAmount,
        String expenseDate,
        String firstPaymentDate,
        String expenseDescription) implements TransactionData {

    public CreditExpenseData(CreditExpense creditExpense) {
        this(creditExpense.getAccount().getId(),TransactionType.EXPENSE,creditExpense.getId(),
                creditExpense.getPaymentsNumber(),creditExpense.getPaymentAmount(),
                creditExpense.getTransactionDateTime().toString(),
                creditExpense.getFirstPaymentDate().toString(),creditExpense.getTransactionDescription());
    }

    public Double getExpenseTotal() {
        return paymentAmount*paymentsNumber;
    }
}
