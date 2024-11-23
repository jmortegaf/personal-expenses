package com.jmortegaf.personal_expenses.validators.debit.expenses;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.dto.ExpenseData;
import com.jmortegaf.personal_expenses.dto.TransactionData;
import com.jmortegaf.personal_expenses.exceptions.InvalidExpenseException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.models.DebitAccount;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.validators.credit.expenses.CreditExpenseValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ExpenseInsufficientBalanceValidator implements ExpenseValidator {
    @Override
    public void validate(User user, Account account, TransactionData transactionData) {
        if (((DebitAccount) account).getBalance() < ((ExpenseData) transactionData).expenseAmount())
            throw new InvalidExpenseException("Insufficient balance for this transaction");
    }
}
