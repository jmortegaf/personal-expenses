package com.jmortegaf.personal_expenses.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    private Long id;
    private LocalDateTime dateTime;
    private Double amount;
    private String description;

}
