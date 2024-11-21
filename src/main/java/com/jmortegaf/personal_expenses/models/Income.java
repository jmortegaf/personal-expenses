package com.jmortegaf.personal_expenses.models;

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
public class Income extends Transaction{

    private Double incomeAmount;

    public Income(Account account,String incomeDateTime, Double incomeAmount, String incomeDescription) {
        super(account,incomeDateTime,incomeDescription);
        this.incomeAmount=incomeAmount;
    }
}
