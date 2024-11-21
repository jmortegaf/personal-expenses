package com.jmortegaf.personal_expenses.repositories;

import com.jmortegaf.personal_expenses.models.Transaction;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByAccountId(@Valid Long id);
}
