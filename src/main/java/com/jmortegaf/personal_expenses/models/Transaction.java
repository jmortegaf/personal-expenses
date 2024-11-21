package com.jmortegaf.personal_expenses.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "transactions", indexes = {
        @Index(name = "idx_account_id", columnList = "account_id")
})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime transactionDateTime;
    private String transactionDescription;

    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    public Transaction(Account account,String transactionDateTime, String transactionDescription) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.account=account;
        this.transactionDateTime = LocalDateTime.parse(transactionDateTime,formatter);
        this.transactionDescription = transactionDescription;
    }
}
