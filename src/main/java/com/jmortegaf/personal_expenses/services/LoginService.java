package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.dto.LoginData;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
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

    private AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginService(AuthenticationManager authenticationManager,
                        TokenService tokenService){
        this.authenticationManager=authenticationManager;
        this.tokenService=tokenService;
    }

    public Map<String,String> login(@Valid LoginData loginData){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                loginData.userName(),loginData.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return Map.of("status","success",
                "message","user successfully logged in",
                "token",JWTToken);
    }

}
