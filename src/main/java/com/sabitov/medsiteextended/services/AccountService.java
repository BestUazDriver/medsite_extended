package com.sabitov.medsiteextended.services;


import com.sabitov.medsiteextended.dto.SignInDto;
import com.sabitov.medsiteextended.dto.SignUpDto;
import com.sabitov.medsiteextended.models.Account;

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
}
