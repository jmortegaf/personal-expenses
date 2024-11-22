package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.models.DebitAccount;

import java.util.List;

public record BalanceData(
        Double debitBalance,
        Double creditBalance,
        Double totalCreditLimit,
        Double totalCreditUsed) {
}