package com.jmortegaf.personal_expenses.models;

import com.jmortegaf.personal_expenses.dto.DepositData;
import com.jmortegaf.personal_expenses.dto.ExpenseData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DebitAccount extends Account{

    private Double balance;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Expense> expenseList;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Deposit> depositList;


    public DebitAccount(String accountName, User user) {
        super(accountName, user);
        balance = 0.0;
    }

    public void addExpense(ExpenseData expenseData) {
        expenseList.add(new Expense(this,expenseData));
        balance-=expenseData.expenseAmount();
    }

    public void addDeposit(DepositData depositData) {
        depositList.add(new Deposit(this,depositData));
        balance+=depositData.depositAmount();
    }
}
