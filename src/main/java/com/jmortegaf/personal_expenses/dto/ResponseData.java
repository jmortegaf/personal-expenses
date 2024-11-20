package com.jmortegaf.personal_expenses.dto;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record ResponseData(HttpStatus statusCode, String status, String message) {

    public Map<String, String> getBody() {
        return Map.of("status",status,
                "message",message);
    }
}
