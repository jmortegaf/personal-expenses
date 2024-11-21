package com.jmortegaf.personal_expenses.controllers;

import com.jmortegaf.personal_expenses.services.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/get-transactions/{id}")
    public ResponseEntity<?> getTransactions(@PathVariable @Valid Long id){
        var result=transactionsService.getTransactions(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-transaction/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable @Valid Long id){
        var result=transactionsService.getTransaction(id);
        return ResponseEntity.ok(result);
    }

}
