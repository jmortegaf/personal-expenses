package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.DebitAccount;

public record DebitAccountData(
        String accountName,
        AccountType accountType,
        Double balance,
        Boolean active) implements AccountData{

    public DebitAccountData(DebitAccount account) {
        this(account.getAccountName(),AccountType.DEBIT,account.getBalance(),account.getActive());
    }

}
