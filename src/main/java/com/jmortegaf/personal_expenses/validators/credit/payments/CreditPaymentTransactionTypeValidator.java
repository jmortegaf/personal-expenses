package com.jmortegaf.personal_expenses.validators.credit.payments;

import com.jmortegaf.personal_expenses.dto.CreditExpenseData;
import com.jmortegaf.personal_expenses.dto.TransactionData;
import com.jmortegaf.personal_expenses.exceptions.InvalidExpenseException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.validators.credit.expenses.CreditExpenseValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CreditPaymentTransactionTypeValidator implements CreditExpenseValidator {
    @Override
    public void validate(User user, Account account, TransactionData transactionData) {
        if(!(transactionData instanceof CreditPaymentValidator))
            throw new InvalidExpenseException("Account type incompatible with transaction type");
    }
}
