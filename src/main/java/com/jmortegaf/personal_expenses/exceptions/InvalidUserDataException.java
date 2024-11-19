package com.jmortegaf.personal_expenses.exceptions;

public class InvalidUserDataException extends RuntimeException{

    public InvalidUserDataException(String message) {
        super(message);
    }
}
