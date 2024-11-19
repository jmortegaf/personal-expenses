package com.jmortegaf.personal_expenses.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private Long id;
    private String accountName;
    private List<Expense> expenseList;

    public Account(){}

    public Account(String accountName){
        this.accountName=accountName;
        expenseList=new ArrayList<>();
    }

}
