package com.jmortegaf.personal_expenses.validators.debit.expenses;

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
@Order(1)
public class ExpenseAccountTypeValidator implements ExpenseValidator {
    @Override
    public void validate(User user, Account account, TransactionData transactionData) {
        if(!(account instanceof DebitAccount))
            throw new InvalidExpenseException("Account type is not compatible with transaction type");
    }
}
