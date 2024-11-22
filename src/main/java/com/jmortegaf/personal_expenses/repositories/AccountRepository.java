package com.jmortegaf.personal_expenses.repositories;

import com.jmortegaf.personal_expenses.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByUserId(Long id);
}
