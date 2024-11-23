package com.jmortegaf.personal_expenses.models;

import com.jmortegaf.personal_expenses.dto.ExpenseData;
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

    public Expense(Account account, ExpenseData expenseData) {
        super(account,expenseData.expenseDate(),expenseData.expenseDescription());
        this.expenseAmount=expenseData.expenseAmount();
    }
}
