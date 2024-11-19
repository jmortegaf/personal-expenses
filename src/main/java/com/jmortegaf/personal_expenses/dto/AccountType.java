package com.jmortegaf.personal_expenses.dto;

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
        throw new IllegalArgumentException("Invalid account type: "+text);
    }
}
