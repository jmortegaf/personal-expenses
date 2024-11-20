package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;

public enum AccountType {

    CREDIT("Credit"),
    DEBIT("Debit");

    private String accountType;


    AccountType(String accountType){
        this.accountType=accountType;
    }
    public static AccountType fromString(String text){
        for(AccountType type :AccountType.values()){
            if(type.accountType.equalsIgnoreCase(text))
                return type;
        }
        throw new InvalidAccountDataException("Invalid account type");
    }
}
