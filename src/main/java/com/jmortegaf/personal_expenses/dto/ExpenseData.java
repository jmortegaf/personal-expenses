package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.CreditExpense;
import com.jmortegaf.personal_expenses.models.Expense;

public record ExpenseData(
        Long accountId,
        TransactionType transactionType,
        Long transactionId,
        Double expenseAmount,
        String expenseDate,
        String expenseDescription) implements TransactionData {

    public ExpenseData(Expense transaction) {
        this(transaction.getAccount().getId(),
                TransactionType.EXPENSE,
                transaction.getId(),
                transaction.getExpenseAmount(),
                transaction.getTransactionDateTime().toString(),
                transaction.getTransactionDescription());
    }
}
