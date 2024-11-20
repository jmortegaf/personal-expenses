package com.jmortegaf.personal_expenses.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DebitAccount extends Account{

    private Double balance;

    public DebitAccount(String accountName) {
        super(accountName);
        balance=0.0;
    }
}
