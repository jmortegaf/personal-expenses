package com.jmortegaf.personal_expenses.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccount extends Account{

    private Double creditLimit;
    private Integer closingDate;
    private Integer dueDate;
    private List<CreditPayment> paymentList;

    public CreditAccount(String accountName,double creditLimit, int closingDate, int dueDate) {
        super(accountName);
        this.creditLimit=creditLimit;
        this.closingDate=closingDate;
        this.dueDate=dueDate;
    }

    public void addExpense(String expenseDescription, LocalDateTime expenseDate,
                           Integer paymentsNumber,Double paymentAmount) {

    }
}
