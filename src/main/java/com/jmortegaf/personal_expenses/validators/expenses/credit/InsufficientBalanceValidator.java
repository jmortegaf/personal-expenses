package com.jmortegaf.personal_expenses.validators.expenses.credit;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.exceptions.InvalidExpenseException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.models.User;
import org.springframework.stereotype.Component;

@Component
public class InsufficientBalanceValidator implements AddExpenseValidator{
    @Override
    public void validate(User user, Account account, CreditExpenseData creditExpenseData) {
        if(((CreditAccount)account).getCurrentBalance()<creditExpenseData.getExpenseTotal())
            throw new InvalidExpenseException("Insufficient balance for this transaction");
    }
}
