package com.jmortegaf.personal_expenses.components;

import com.jmortegaf.personal_expenses.exceptions.UserAuthenticationErrorException;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UtilsComponent {

    private final UserRepository userRepository;

    public UtilsComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return (User) userRepository.findByUserName(userDetails.getUsername());
        }
        throw new UserAuthenticationErrorException("User authentication error");
    }
}