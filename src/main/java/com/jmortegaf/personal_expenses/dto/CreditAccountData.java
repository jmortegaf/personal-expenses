package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.CreditAccount;

import java.util.Optional;

public record CreditAccountData(
        String accountName,
        AccountType accountType,
        Double creditLimit,
        Integer closingDate,
        Integer dueDate,
        Boolean active) implements AccountData{


    public CreditAccountData(CreditAccount account) {
        this(account.getAccountName(),
                AccountType.CREDIT,
                account.getCreditLimit(),
                account.getClosingDate(),
                account.getDueDate(),
                account.getActive());
    }
}
