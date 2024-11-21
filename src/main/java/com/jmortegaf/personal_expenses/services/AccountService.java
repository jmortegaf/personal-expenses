package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.dto.*;
import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.models.DebitAccount;
import com.jmortegaf.personal_expenses.repositories.AccountRepository;
import com.jmortegaf.personal_expenses.validators.expenses.credit.AddExpenseValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final List<AddExpenseValidator> addExpenseValidators;

    public AccountService(AccountRepository accountRepository,
                          List<AddExpenseValidator> addExpenseValidators){

        this.accountRepository=accountRepository;
        this.addExpenseValidators=addExpenseValidators;
    }

    public ResponseData createAccount(@Valid CreateAccountData createAccountData){
        Account account;
        switch (AccountType.fromString(createAccountData.accountType())){
            case CREDIT:
                account=new CreditAccount(createAccountData.accountName());
                accountRepository.save(account);
                break;
            case DEBIT:
                account=new DebitAccount(createAccountData.accountName());
                accountRepository.save(account);
                break;
            default:
                throw new InvalidAccountDataException("Invalid account type");
        }
        return new ResponseData(HttpStatus.OK,"Ok","Account created successfully");
    }

    public AccountData getAccount(@Valid Long id) {
        var account=accountRepository.findById(id);
        if(account.isPresent()){
            if(account.get() instanceof CreditAccount)
                return new CreditAccountData((CreditAccount)account.get());
            else if(account.get() instanceof DebitAccount)
                return new DebitAccountData((DebitAccount)account.get());
        }
        throw new InvalidAccountDataException("Invalid");
    }

    @Transactional
    public ResponseData addCreditExpense(@Valid Long id, @Valid CreditExpenseData creditExpenseData) {
        var query = accountRepository.findById(id);
        if(query.isPresent()){
            if(query.get() instanceof CreditAccount account){
                addExpenseValidators.forEach(validator->validator.validate(null,account,creditExpenseData));
                account.addExpense(creditExpenseData);
                return new ResponseData(HttpStatus.OK,"Ok","Expense added successfully");
            }
        }
        throw new InvalidAccountDataException("Account doesn't exists");
    }

    @Transactional
    public ResponseData addCreditPayment(@Valid Long id, @Valid CreditPaymentData creditPaymentData) {
        var query = accountRepository.findById(id);
        if(query.isPresent()){
            if(query.get() instanceof CreditAccount account){
                account.addPayment(creditPaymentData);
                return new ResponseData(HttpStatus.OK,"Ok","Payment added successfully");
            }
        }
        throw new InvalidAccountDataException("Account doesn't exists");
    }

}
