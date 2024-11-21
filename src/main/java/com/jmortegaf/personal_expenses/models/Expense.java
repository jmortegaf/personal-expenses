package com.jmortegaf.personal_expenses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense extends Transaction {

    private Double expenseAmount;

    public Expense(Account account,String expenseDateTime, Double expenseAmount, String expenseDescription) {
        super(account,expenseDateTime,expenseDescription);
        this.expenseAmount = expenseAmount;
    }
}
