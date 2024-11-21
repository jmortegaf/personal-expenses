package com.jmortegaf.personal_expenses.models;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "credit_expenses")
public class CreditExpense extends Expense{

    private Integer paymentsNumber;
    private Double paymentAmount;
    private LocalDateTime firstPaymentDate;

    public CreditExpense(Account account,CreditExpenseData creditExpenseData) {
        super(account,creditExpenseData.expenseDate(),creditExpenseData.getExpenseTotal(),creditExpenseData.expenseDescription());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        paymentsNumber= creditExpenseData.paymentsNumber();
        paymentAmount= creditExpenseData.paymentAmount();
        firstPaymentDate=LocalDateTime.parse(creditExpenseData.firstPaymentDate(),formatter);
    }
}
