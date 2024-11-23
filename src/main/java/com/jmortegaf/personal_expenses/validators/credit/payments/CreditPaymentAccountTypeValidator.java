package com.jmortegaf.personal_expenses.validators.credit.payments;

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
public class CreditPaymentAccountTypeValidator implements CreditPaymentValidator {
    @Override
    public void validate(User user, Account account, TransactionData transactionData) {
        if(!(account instanceof CreditAccount))
            throw new InvalidExpenseException("Account type is not compatible with transaction type");
    }
}
