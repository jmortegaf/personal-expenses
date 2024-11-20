package com.jmortegaf.personal_expenses.exceptions;

public class InvalidAccountDataException extends RuntimeException{

    public InvalidAccountDataException(String message) {
        super(message);
    }
}
