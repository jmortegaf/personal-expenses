package com.jmortegaf.personal_expenses.dto;

import java.util.Map;

public record ErrorData(String error, String message) {

    public Map<String, String> getBody(){
        return Map.of("error",error,
                "message",message);
    }
}
