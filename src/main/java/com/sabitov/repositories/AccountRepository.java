package com.sabitov.repositories;

import com.sabitov.models.Account;
import com.sabitov.models.Account.Role;
import com.sabitov.models.Ill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByConfirmCode(String uuid);

    Optional<Account> findAccountByEmail(String email);

    Account findByEmail(String email);

    List<Account> findAccountsByRoleIsNot(Role role);

    List<Account> findAccountsByEmailContainingIgnoreCaseAndRoleIsNot(String email, Role role);
}
