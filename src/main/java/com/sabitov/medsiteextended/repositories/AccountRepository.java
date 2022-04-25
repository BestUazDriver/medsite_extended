package com.sabitov.medsiteextended.repositories;

import com.sabitov.medsiteextended.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByConfirmCode(String uuid);

    Optional<Account> findAccountByEmail(String email);

    Account findByEmail(String email);

    List<Account> findAccountsByRoleIsNot(Account.Role role);

    List<Account> findAccountsByEmailContainingIgnoreCaseAndRoleIsNot(String email, Account.Role role);
}
