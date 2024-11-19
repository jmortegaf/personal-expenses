package com.jmortegaf.personal_expenses.repositories;

import com.jmortegaf.personal_expenses.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByUserEmail(String s);
}
