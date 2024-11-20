package com.jmortegaf.personal_expenses.validators.expenses.credit;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.User;

public interface AddExpenseValidator {

    void validate(User user, Account account, CreditExpenseData creditExpenseData);
}
