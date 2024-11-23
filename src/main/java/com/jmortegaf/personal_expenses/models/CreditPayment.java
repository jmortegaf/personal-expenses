package com.jmortegaf.personal_expenses.models;

import com.jmortegaf.personal_expenses.dto.CreditPaymentData;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "credit_payments")
public class CreditPayment extends Deposit {

    public CreditPayment(Account account, CreditPaymentData creditPaymentData) {
        super(account, creditPaymentData.paymentDateTime(), creditPaymentData.paymentAmount(),creditPaymentData.paymentDescription());
    }
}
