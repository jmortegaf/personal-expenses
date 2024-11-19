package com.jmortegaf.personal_expenses.dto;

import com.jmortegaf.personal_expenses.models.User;

public record UserData(
        String userName,
        String userEmail,
        String userPassword){
    public UserData(User user){
        this(user.getUsername(), user.getUserEmail(), user.getUserPassword());
    }
}
