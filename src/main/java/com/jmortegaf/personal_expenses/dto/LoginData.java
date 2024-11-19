package com.jmortegaf.personal_expenses.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginData(
        @NotBlank String userName,
        @NotBlank String password) {
}
