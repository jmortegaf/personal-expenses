package com.jmortegaf.personal_expenses.validators.credit.payments;

import com.jmortegaf.personal_expenses.dto.TransactionData;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.User;

public interface CreditPaymentValidator {

    void validate(User user, Account account, TransactionData transactionData);
}
