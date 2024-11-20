package com.jmortegaf.personal_expenses.exceptions;

public class InvalidExpenseException extends RuntimeException{
    public InvalidExpenseException(String message) {
        super(message);
    }
}
