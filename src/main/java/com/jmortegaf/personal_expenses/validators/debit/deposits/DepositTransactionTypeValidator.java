package com.jmortegaf.personal_expenses.validators.debit.deposits;

import com.jmortegaf.personal_expenses.dto.DepositData;
import com.jmortegaf.personal_expenses.dto.PaymentData;
import com.jmortegaf.personal_expenses.dto.TransactionData;
import com.jmortegaf.personal_expenses.exceptions.InvalidExpenseException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.validators.credit.expenses.CreditExpenseValidator;
import com.jmortegaf.personal_expenses.validators.credit.payments.CreditPaymentValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DepositTransactionTypeValidator implements DepositValidator {
    @Override
    public void validate(User user, Account account, TransactionData transactionData) {
        if(!(transactionData instanceof DepositData))
            throw new InvalidExpenseException("Account type incompatible with transaction type");
    }
}
