package com.jmortegaf.personal_expenses.controllers;

import com.jmortegaf.personal_expenses.dto.LoginData;
import com.jmortegaf.personal_expenses.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginData loginData){
        return ResponseEntity.ok(loginService.login(loginData));
    }
}
