package com.jmortegaf.personal_expenses.dto;

public record CreateAccountData(
        String accountName,
        AccountType accountType,
        AccountDetails accountDetails){}
