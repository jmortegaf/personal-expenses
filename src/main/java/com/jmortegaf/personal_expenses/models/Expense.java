package com.jmortegaf.personal_expenses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime expenseDateTime;
    private Double expenseAmount;
    private String expenseDescription;

    public Expense(String expenseDateTime, Double expenseAmount, String expenseDescription) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.expenseDateTime = LocalDateTime.parse(expenseDateTime,formatter);
        this.expenseAmount = expenseAmount;
        this.expenseDescription = expenseDescription;
    }
}
