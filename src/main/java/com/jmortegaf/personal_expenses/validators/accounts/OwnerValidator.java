package com.jmortegaf.personal_expenses.validators.accounts;

import com.jmortegaf.personal_expenses.dto.TransactionData;
import com.jmortegaf.personal_expenses.exceptions.InvalidExpenseException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.validators.credit.expenses.CreditExpenseValidator;

public class OwnerValidator implements AccountGenericValidator {

    @Override
    public void validate(User user, Account account) {
        if(!user.equals(account.getUser()))
            throw new InvalidExpenseException("User is not the owner of the account");
    }
}
