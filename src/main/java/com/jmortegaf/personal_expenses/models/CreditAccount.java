package com.jmortegaf.personal_expenses.models;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.dto.CreditPaymentData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditAccount extends Account{

    private Double creditLimit;
    private Double usedCredit;
    private Integer closingDate;
    private Integer dueDate;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<CreditExpense> expenseList;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<CreditPayment> paymentList;


    public CreditAccount(String accountName) {
        super(accountName);
        creditLimit=0.0;
        usedCredit=0.0;
        closingDate=1;
        dueDate=closingDate+15;
        expenseList=new ArrayList<>();
    }

    public Double getCurrentBalance(){
        return creditLimit-usedCredit;
    }

    public void addExpense(CreditExpenseData creditExpenseData) {
        expenseList.add(new CreditExpense(this,creditExpenseData));
        usedCredit+=creditExpenseData.getExpenseTotal();
    }

    public void addPayment(@Valid CreditPaymentData creditPaymentData) {
        paymentList.add(new CreditPayment(this,creditPaymentData));
        usedCredit-=creditPaymentData.paymentAmount();
    }
}
