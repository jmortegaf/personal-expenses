package com.jmortegaf.personal_expenses.controllers;

import com.jmortegaf.personal_expenses.dto.UserData;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
import com.jmortegaf.personal_expenses.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        System.out.println("hi");
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserData userData){
        return ResponseEntity.ok(userService.createUser(userData));
    }


}