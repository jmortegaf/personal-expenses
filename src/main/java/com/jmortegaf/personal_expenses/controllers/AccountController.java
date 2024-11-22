package com.jmortegaf.personal_expenses.controllers;

import com.jmortegaf.personal_expenses.dto.CreateAccountData;
import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.dto.CreditPaymentData;
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

    // CREDIT

    @PostMapping("/credit/add-expense/{id}")
    public ResponseEntity<?> addCreditExpense(@PathVariable @Valid Long id,
                                              @RequestBody @Valid CreditExpenseData creditExpenseData){
        var result=accountService.addCreditExpense(id,creditExpenseData);
        return ResponseEntity.status(result.statusCode()).body(result.getBody());
    }

    @PostMapping("/credit/add-payment/{id}")
    public ResponseEntity<?> addCreditPayment(@PathVariable @Valid Long id,
                                              @RequestBody @Valid CreditPaymentData creditPaymentData){
        var result=accountService.addCreditPayment(id,creditPaymentData);
        return ResponseEntity.status(result.statusCode()).body(result.getBody());
    }

    // DEBIT

//    @PostMapping("/debit/add-expense/{id}")

}
