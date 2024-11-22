package com.jmortegaf.personal_expenses.services;

import com.jmortegaf.personal_expenses.components.UtilsComponent;
import com.jmortegaf.personal_expenses.dto.BalanceData;
import com.jmortegaf.personal_expenses.dto.UserData;
import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;
import com.jmortegaf.personal_expenses.models.Account;
import com.jmortegaf.personal_expenses.models.CreditAccount;
import com.jmortegaf.personal_expenses.models.DebitAccount;
import com.jmortegaf.personal_expenses.models.User;
import com.jmortegaf.personal_expenses.repositories.AccountRepository;
import com.jmortegaf.personal_expenses.repositories.UserRepository;
import com.jmortegaf.personal_expenses.validators.users.UserRegisterValidator;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final List<UserRegisterValidator> userRegisterValidators;
    private final PasswordEncoder passwordEncoder;
    private final UtilsComponent utilsComponent;

    public UserService(UserRepository userRepository, List<UserRegisterValidator> userRegisterValidators,
                       PasswordEncoder passwordEncoder, UtilsComponent utilsComponent,
                       AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.userRegisterValidators = userRegisterValidators;
        this.passwordEncoder = passwordEncoder;
        this.utilsComponent = utilsComponent;
        this.accountRepository = accountRepository;
    }

    public List<UserData> getUsers() {
        return userRepository.findAll().stream().map(UserData::new).toList();
    }

    public Map<String,String> createUser(@Valid UserData userData) {
        userRegisterValidators.forEach(validator->validator.validate(userData));
        var hashedPassword=passwordEncoder.encode(userData.userPassword());
        var user=new User(userData,hashedPassword);
        userRepository.save(user);
        return Map.of("status","ok",
                "message","Success");
    }

    public BalanceData getUserBalance() {
        var user=utilsComponent.getUser();
        var accounts=accountRepository.findByUserId(user.getId());
        return formatBalance(accounts);
    }

    private static BalanceData formatBalance(List<Account> accounts){
        double debitBalance = 0d;
        double totalCreditLimit = 0d;
        double totalCreditUsed = 0d;
        debitBalance = accounts.stream().mapToDouble(account -> {
            if (account instanceof DebitAccount)
                return ((DebitAccount) account).getBalance();
            return 0d;
        }).sum();
        totalCreditLimit=accounts.stream().mapToDouble(account->{
            if(account instanceof CreditAccount)
                return ((CreditAccount) account).getCreditLimit();
            return 0d;
        }).sum();
        totalCreditUsed=accounts.stream().mapToDouble(account->{
            if(account instanceof CreditAccount)
                return ((CreditAccount) account).getUsedCredit();
            return 0d;
        }).sum();
        return new BalanceData(debitBalance,totalCreditLimit-totalCreditUsed,
                totalCreditLimit,totalCreditUsed);
    }
}
