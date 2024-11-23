package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.components.UtilsComponent;
import com.jmortegaf.personal_expenses.dto.*;
import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.models.DebitAccount;
import com.jmortegaf.personal_expenses.repositories.AccountRepository;
import com.jmortegaf.personal_expenses.validators.accounts.AccountGenericValidator;
import com.jmortegaf.personal_expenses.validators.credit.expenses.CreditExpenseValidator;
import com.jmortegaf.personal_expenses.validators.credit.payments.CreditPaymentValidator;
import com.jmortegaf.personal_expenses.validators.debit.deposits.DepositValidator;
import com.jmortegaf.personal_expenses.validators.debit.expenses.ExpenseValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitService {

    private final AccountRepository accountRepository;
    private final UtilsComponent utilsComponent;
    private final List<AccountGenericValidator> accountGenericValidators;
    private final List<ExpenseValidator> expenseValidators;
    private final List<DepositValidator> depositValidators;

    public DebitService(AccountRepository accountRepository, UtilsComponent utilsComponent,
                        List<AccountGenericValidator> accountGenericValidators,
                        List<ExpenseValidator> expenseValidators, List<DepositValidator> depositValidators) {

        this.accountRepository = accountRepository;
        this.utilsComponent = utilsComponent;
        this.accountGenericValidators = accountGenericValidators;
        this.expenseValidators = expenseValidators;
        this.depositValidators = depositValidators;
    }

    @Transactional
    public ResponseData addExpense(@Valid Long id, @Valid ExpenseData expenseData) {
        var account = accountRepository.findById(id);
        var user=utilsComponent.getUser();
        if(account.isPresent()){
            accountGenericValidators.forEach(validator->validator.validate(user,account.get()));
            expenseValidators.forEach(validator->validator.validate(user,account.get(),expenseData));
            ((DebitAccount)account.get()).addExpense(expenseData);
            return new ResponseData(HttpStatus.OK,"Ok","Expense added successfully");
        }
        throw new InvalidAccountDataException("Account doesn't exists");
    }

    @Transactional
    public ResponseData addDeposit(@Valid Long id, @Valid DepositData depositData) {
        var account = accountRepository.findById(id);
        var user=utilsComponent.getUser();
        if(account.isPresent()){
            accountGenericValidators.forEach(validator->validator.validate(user,account.get()));
            depositValidators.forEach(validator->validator.validate(user,account.get(),depositData));
            ((DebitAccount)account.get()).addDeposit(depositData);
            return new ResponseData(HttpStatus.OK,"Ok","Payment added successfully");
        }
        throw new InvalidAccountDataException("Account doesn't exists");
    }
}
