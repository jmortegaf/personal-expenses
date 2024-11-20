package com.jmortegaf.personal_expenses.dto;

import java.time.LocalDateTime;

public record CreditExpenseData(
        Integer paymentsNumber,
        Double paymentValue,
        String expenseDate,
        String firstPaymentDate,
        String expenseDescription) {
    public Double getExpenseTotal() {
        return paymentValue*paymentsNumber;
    }
}
