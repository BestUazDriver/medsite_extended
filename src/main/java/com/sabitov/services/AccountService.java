package com.sabitov.services;


import com.sabitov.dto.SignInDto;
import com.sabitov.dto.SignUpDto;
import com.sabitov.models.Account;
import com.sabitov.models.Ill;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    void signUp(SignUpDto signUpDto);

    String confirmAccount(String uuid);

    boolean emailExists(String email);

    boolean passwordValid(String password);

    boolean isAuth(SignInDto signInDto);

    Optional<Account> findAccountByEmail(String email);

    boolean checkConfirm(String email);

    void save(Account account);

    List<Ill> findAllIllnessByAccountId(Long id);

    void addIll(Ill ill, Account account);

    void deleteIll(Account account, Ill ill);
}
