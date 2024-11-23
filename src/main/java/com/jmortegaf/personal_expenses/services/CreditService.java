package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.components.UtilsComponent;
import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.dto.CreditPaymentData;
import com.jmortegaf.personal_expenses.dto.ResponseData;
import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.repositories.AccountRepository;
import com.jmortegaf.personal_expenses.validators.accounts.AccountGenericValidator;
import com.jmortegaf.personal_expenses.validators.credit.expenses.CreditExpenseValidator;
import com.jmortegaf.personal_expenses.validators.credit.payments.CreditPaymentValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    private final AccountRepository accountRepository;
    private final UtilsComponent utilsComponent;
    private final List<AccountGenericValidator> accountGenericValidators;
    private final List<CreditExpenseValidator> creditExpenseValidators;
    private final List<CreditPaymentValidator> creditPaymentValidators;

    public CreditService(AccountRepository accountRepository, UtilsComponent utilsComponent,
                         List<AccountGenericValidator> accountGenericValidators,
                         List<CreditExpenseValidator> creditExpenseValidators,
                         List<CreditPaymentValidator> creditPaymentValidators) {

        this.accountRepository = accountRepository;
        this.utilsComponent = utilsComponent;
        this.accountGenericValidators=accountGenericValidators;
        this.creditExpenseValidators = creditExpenseValidators;
        this.creditPaymentValidators = creditPaymentValidators;
    }

    @Transactional
    public ResponseData addExpense(@Valid Long id, @Valid CreditExpenseData creditExpenseData) {
        var account = accountRepository.findById(id);
        var user=utilsComponent.getUser();
        if(account.isPresent()){
            accountGenericValidators.forEach(validator->validator.validate(user,account.get()));
            creditExpenseValidators.forEach(validator->validator.validate(user,account.get(),creditExpenseData));
            ((CreditAccount)account.get()).addExpense(creditExpenseData);
            return new ResponseData(HttpStatus.OK,"Ok","Expense added successfully");
        }
        throw new InvalidAccountDataException("Account doesn't exists");
    }

    @Transactional
    public ResponseData addPayment(@Valid Long id, @Valid CreditPaymentData creditPaymentData) {
        var account = accountRepository.findById(id);
        var user=utilsComponent.getUser();
        if(account.isPresent()){
            accountGenericValidators.forEach(validator->validator.validate(user,account.get()));
            creditPaymentValidators.forEach(validator->validator.validate(user,account.get(),creditPaymentData));
            ((CreditAccount)account.get()).addPayment(creditPaymentData);
            return new ResponseData(HttpStatus.OK,"Ok","Payment added successfully");
        }
        throw new InvalidAccountDataException("Account doesn't exists");
    }

}
