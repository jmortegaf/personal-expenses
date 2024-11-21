package com.jmortegaf.personal_expenses.validators.users;

import com.jmortegaf.personal_expenses.dto.UserData;
import com.jmortegaf.personal_expenses.exceptions.InvalidUserDataException;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UniqueUserNameValidator implements UserRegisterValidator {

    private final UserRepository userRepository;

    public UniqueUserNameValidator(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void validate(UserData userData) {
        var user=userRepository.existsByUserName(userData.userName());
        if(user)throw new InvalidUserDataException("Username is already in use");
    }
}
