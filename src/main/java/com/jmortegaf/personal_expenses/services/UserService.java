package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.dto.UserData;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
import com.jmortegaf.personal_expenses.validators.users.UserRegisterValidator;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final List<UserRegisterValidator> userRegisterValidators;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,
                       List<UserRegisterValidator> userRegisterValidators){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.userRegisterValidators=userRegisterValidators;
    }

    public List<UserData> getUsers() {
        return userRepository.findAll().stream().map(UserData::new).toList();
    }

    public Map<String,String> createUser(@Valid UserData userData) {
        userRegisterValidators.forEach(validator->validator.validate(userData));
        var hashedPassword=passwordEncoder.encode(userData.userPassword());
        var user=new User(userData,hashedPassword);
        userRepository.save(user);
        return Map.of("status","ok",
                "message","Success");
    }
}
