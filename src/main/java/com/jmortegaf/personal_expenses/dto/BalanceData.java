package com.jmortegaf.personal_expenses.dto;

public record BalanceData(
        Double debitBalance,
        Double creditBalance,
        Double totalCreditLimit,
        Double totalCreditUsed) {
}
