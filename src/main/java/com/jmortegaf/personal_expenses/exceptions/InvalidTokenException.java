package com.jmortegaf.personal_expenses.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String msg) {
        super(msg);
    }
}
