package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.dto.LoginData;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
import com.jmortegaf.personal_expenses.validators.users.ExistsUserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService{

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private ExistsUserValidator existsUserValidator;

    public LoginService(AuthenticationManager authenticationManager,
                        TokenService tokenService,ExistsUserValidator existsUserValidator){
        this.authenticationManager=authenticationManager;
        this.tokenService=tokenService;
        this.existsUserValidator=existsUserValidator;
    }

    public Map<String,String> login(@Valid LoginData loginData){
        existsUserValidator.validate(loginData.userName());
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                loginData.userName(),loginData.password());

        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return Map.of("status","success",
                "message","user successfully logged in",
                "token",JWTToken);
    }

}
