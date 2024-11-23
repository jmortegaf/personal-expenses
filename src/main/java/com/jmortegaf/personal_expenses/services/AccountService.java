package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.components.UtilsComponent;
import com.jmortegaf.personal_expenses.dto.*;
import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;
import com.jmortegaf.personal_expenses.models.*;
import com.jmortegaf.personal_expenses.repositories.AccountRepository;
import com.jmortegaf.personal_expenses.repositories.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final UtilsComponent utilsComponent;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(UtilsComponent utilsComponent,AccountRepository accountRepository,
                          TransactionRepository transactionRepository){

        this.utilsComponent=utilsComponent;
        this.accountRepository=accountRepository;
        this.transactionRepository=transactionRepository;
    }

    public ResponseData createAccount(@Valid CreateAccountData createAccountData){
        Account account;
        var user=utilsComponent.getUser();
        switch (AccountType.fromString(createAccountData.accountType())){
            case CREDIT:
                account=new CreditAccount(createAccountData.accountName(),user);
                accountRepository.save(account);
                break;
            case DEBIT:
                account=new DebitAccount(createAccountData.accountName(),user);
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

    public List<TransactionData> getTransactions(@Valid Long id) {
        var account = accountRepository.findById(id);
        if (account.isPresent()) {
            var transactions = transactionRepository.findByAccountId(id);
            List<TransactionData> transactionsList = new ArrayList<>();
            if(account.get() instanceof CreditAccount){
                transactions.forEach(transaction -> {
                    if (transaction instanceof CreditPayment)
                        transactionsList.add(new CreditPaymentData((CreditPayment) transaction));
                    else transactionsList.add(new CreditExpenseData((CreditExpense) transaction));
                });
            }
            else{
                transactions.forEach(transaction -> {
                    if(transaction instanceof Deposit)
                        transactionsList.add(new DepositData((Deposit)transaction));
                    else transactionsList.add(new ExpenseData((Expense)transaction));
                });
            }
            return transactionsList;
        }
        throw new InvalidAccountDataException("The account doesn't exists");
    }

    public TransactionData getTransaction(@Valid Long id) {
        var transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            if(transaction.get().getAccount() instanceof CreditAccount){
                return transaction.get() instanceof CreditPayment ?
                        new CreditPaymentData((CreditPayment) transaction.get()) :
                        new CreditExpenseData((CreditExpense) transaction.get());
            }
            return transaction.get() instanceof Deposit ?
                    new DepositData((Deposit) transaction.get()) :
                    new ExpenseData((Expense) transaction.get());
        }
        throw new InvalidAccountDataException("The transaction doesn't exists");
    }

}
