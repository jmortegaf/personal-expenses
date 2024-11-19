package com.jmortegaf.personal_expenses.validators.expenses;

import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.Expense;

public interface InsufficientBalanceValidator {

    public void validate(Account account, Expense expense);
}
