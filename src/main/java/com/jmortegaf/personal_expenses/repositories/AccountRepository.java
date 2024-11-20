package com.jmortegaf.personal_expenses.repositories;

import com.jmortegaf.personal_expenses.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
