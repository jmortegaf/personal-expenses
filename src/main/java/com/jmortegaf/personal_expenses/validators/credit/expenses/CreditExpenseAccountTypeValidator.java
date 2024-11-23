package com.jmortegaf.personal_expenses.validators.credit.expenses;

import com.jmortegaf.personal_expenses.dto.TransactionData;
import com.jmortegaf.personal_expenses.exceptions.InvalidExpenseException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.models.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CreditExpenseAccountTypeValidator implements CreditExpenseValidator {
    @Override
    public void validate(User user, Account account, TransactionData transactionData) {
        if(!(account instanceof CreditAccount))
            throw new InvalidExpenseException("Account type is not compatible with transaction type");
    }
}
