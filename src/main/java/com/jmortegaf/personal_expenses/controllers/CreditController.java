package com.jmortegaf.personal_expenses.controllers;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.dto.CreditPaymentData;
import com.jmortegaf.personal_expenses.services.CreditService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/add-expense/{id}")
    public ResponseEntity<?> addCreditExpense(@PathVariable @Valid Long id,
                                              @RequestBody @Valid CreditExpenseData creditExpenseData){
        var result=creditService.addExpense(id,creditExpenseData);
        return ResponseEntity.status(result.statusCode()).body(result.getBody());
    }

    @PostMapping("/add-payment/{id}")
    public ResponseEntity<?> addCreditPayment(@PathVariable @Valid Long id,
                                              @RequestBody @Valid CreditPaymentData creditPaymentData){
        var result=creditService.addPayment(id,creditPaymentData);
        return ResponseEntity.status(result.statusCode()).body(result.getBody());
    }
}
