package com.jmortegaf.personal_expenses.validators.accounts;

import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.User;

public interface AccountGenericValidator {

    void validate(User user, Account account);
}
