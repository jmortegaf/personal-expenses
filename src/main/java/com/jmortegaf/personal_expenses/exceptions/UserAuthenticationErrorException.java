package com.jmortegaf.personal_expenses.exceptions;

public class UserAuthenticationErrorException extends RuntimeException{

    public UserAuthenticationErrorException(String message) {
        super(message);
    }
}
