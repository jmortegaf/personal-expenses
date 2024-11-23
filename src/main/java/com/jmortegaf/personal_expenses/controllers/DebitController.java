package com.jmortegaf.personal_expenses.controllers;

import com.jmortegaf.personal_expenses.dto.CreditPaymentData;
import com.jmortegaf.personal_expenses.dto.DepositData;
import com.jmortegaf.personal_expenses.dto.ExpenseData;
import com.jmortegaf.personal_expenses.services.DebitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/debit")
public class DebitController {

    private final DebitService debitService;

    public DebitController(DebitService debitService) {
        this.debitService = debitService;
    }

    @PostMapping("/add-expense/{id}")
    public ResponseEntity<?> addDebitExpense(@PathVariable @Valid Long id,
                                              @RequestBody @Valid ExpenseData expenseData){
        var result=debitService.addExpense(id,expenseData);
        return ResponseEntity.status(result.statusCode()).body(result.getBody());
    }

    @PostMapping("/add-deposit/{id}")
    public ResponseEntity<?> addCreditPayment(@PathVariable @Valid Long id,
                                              @RequestBody @Valid DepositData depositData){
        var result=debitService.addDeposit(id,depositData);
        return ResponseEntity.status(result.statusCode()).body(result.getBody());
    }
}
