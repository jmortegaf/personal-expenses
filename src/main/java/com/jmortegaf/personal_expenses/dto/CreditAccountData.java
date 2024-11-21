package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.CreditAccount;

public record CreditAccountData(
        String accountName,
        AccountType accountType,
        Double creditLimit,
        Double usedCredit,
        Integer closingDate,
        Integer dueDate,
        Boolean active) implements AccountData{


    public CreditAccountData(CreditAccount account) {
        this(account.getAccountName(),
                AccountType.CREDIT,
                account.getCreditLimit(),
                account.getUsedCredit(),
                account.getClosingDate(),
                account.getDueDate(),
                account.getActive());
    }
}
