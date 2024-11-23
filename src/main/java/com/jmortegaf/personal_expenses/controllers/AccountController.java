package com.jmortegaf.personal_expenses.controllers;

import com.jmortegaf.personal_expenses.dto.CreateAccountData;
import com.jmortegaf.personal_expenses.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody @Valid CreateAccountData createAccountData){
        var result=accountService.createAccount(createAccountData);
        return ResponseEntity.status(result.statusCode())
                .body(result.getBody());
    }
    @GetMapping("/get-account/{id}")
    public ResponseEntity<?> getAccount(@PathVariable @Valid Long id){
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @GetMapping("/get-transactions/{id}")
    public ResponseEntity<?> getTransactions(@PathVariable @Valid Long id){
        var result=accountService.getTransactions(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-transaction/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable @Valid Long id){
        var result=accountService.getTransaction(id);
        return ResponseEntity.ok(result);
    }

}
