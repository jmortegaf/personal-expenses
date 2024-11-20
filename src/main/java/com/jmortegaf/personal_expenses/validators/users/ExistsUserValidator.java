package com.jmortegaf.personal_expenses.validators.users;

import com.jmortegaf.personal_expenses.exceptions.InvalidUserDataException;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ExistsUserValidator {

    private final UserRepository userRepository;

    public ExistsUserValidator(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void validate(String userName){
        var user=userRepository.existsByUserName(userName);
        if(!user)throw new InvalidUserDataException("User doesn't exist");
    }
}
