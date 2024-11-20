package com.jmortegaf.personal_expenses.models;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class CreditExpense extends Expense{

    @ManyToOne
    private Account account;
    private Integer paymentsNumber;
    private Double paymentAmount;
    private LocalDateTime firstPaymentDate;

    public CreditExpense(Account account,CreditExpenseData creditExpenseData) {
        super(creditExpenseData.expenseDate(),creditExpenseData.getExpenseTotal(),creditExpenseData.expenseDescription());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        this.account=account;
        paymentsNumber= creditExpenseData.paymentsNumber();
        paymentAmount= creditExpenseData.paymentValue();
        firstPaymentDate=LocalDateTime.parse(creditExpenseData.firstPaymentDate(),formatter);
    }
}
