package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.dto.CreditPaymentData;
import com.jmortegaf.personal_expenses.dto.TransactionData;
import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;
import com.jmortegaf.personal_expenses.models.CreditExpense;
import com.jmortegaf.personal_expenses.models.CreditPayment;
import com.jmortegaf.personal_expenses.repositories.AccountRepository;
import com.jmortegaf.personal_expenses.repositories.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionsService(AccountRepository accountRepository,
                               TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionData> getTransactions(@Valid Long id) {
        var account = accountRepository.findById(id);
        if (account.isPresent()) {
            var transactions = transactionRepository.findByAccountId(id);
            List<TransactionData> transactionsList = new ArrayList<>();
            transactions.forEach(transaction -> {
                if (transaction instanceof CreditPayment)
                    transactionsList.add(new CreditPaymentData((CreditPayment) transaction));
                else transactionsList.add(new CreditExpenseData((CreditExpense) transaction));
            });
            return transactionsList;
        }
        throw new InvalidAccountDataException("The account doesn't exists");

    }

    public TransactionData getTransaction(@Valid Long id) {
        var transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get() instanceof CreditPayment ?
                    new CreditPaymentData((CreditPayment) transaction.get()) :
                    new CreditExpenseData((CreditExpense) transaction.get());
        }
        throw new InvalidAccountDataException("The transaction doesn't exists");
    }
}
