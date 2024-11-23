package com.jmortegaf.personal_expenses.models;

import com.jmortegaf.personal_expenses.dto.DepositData;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "income")
public class Deposit extends Transaction{

    private Double depositAmount;

    public Deposit(Account account, String depositDateTime, Double depositAmount, String depositDescription) {
        super(account,depositDateTime,depositDescription);
        this.depositAmount =depositAmount;
    }

    public Deposit(Account account, DepositData depositData) {
        super(account,depositData.depositDateTime(),depositData.depositDescription());
        this.depositAmount=depositData.depositAmount();
    }
}
