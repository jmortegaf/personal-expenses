package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;

public enum TransactionType {

    EXPENSE("Expense"),
    PAYMENT("Payment");

    private final String transactionType;


    TransactionType(String transactionType){
        this.transactionType=transactionType;
    }
    public static TransactionType fromString(String text){
        for(TransactionType type :TransactionType.values()){
            if(type.transactionType.equalsIgnoreCase(text))
                return type;
        }
        throw new InvalidAccountDataException("Invalid transaction type");
    }}
