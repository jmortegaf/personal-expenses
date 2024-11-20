package com.jmortegaf.personal_expenses.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountData(
        @NotBlank String accountName,
        @NotBlank String accountType){}
