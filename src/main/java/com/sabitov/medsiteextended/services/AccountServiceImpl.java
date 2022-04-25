package com.sabitov.medsiteextended.services;

import com.sabitov.medsiteextended.dto.SignInDto;
import com.sabitov.medsiteextended.dto.SignUpDto;
import com.sabitov.medsiteextended.models.Account;
import com.sabitov.medsiteextended.repositories.AccountRepository;
import com.sabitov.medsiteextended.utils.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Value("${server.port}")
    String port;

    private final AccountRepository accountRepository;
    private final EmailUtil emailUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(SignUpDto signUpDto) {
        Account account = Account.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .state(Account.State.NOT_CONFIRMED)
                .role(Account.Role.USER)
                .confirmCode(UUID.randomUUID().toString())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();

        accountRepository.save(account);

        Map<String, String> data = new HashMap<>();
        data.put("username", account.getName());
        data.put("confirmation_code", account.getConfirmCode());
        data.put("port", port);

        emailUtil.sendMail(account.getEmail(), "confirm", "confirm_mail.ftlh",
                data);

    }

    @Override
    public String confirmAccount(String uuid) {
        Optional<Account> accountOptional = accountRepository.findAccountByConfirmCode(uuid);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getState().equals(Account.State.NOT_CONFIRMED)) {
                account.setState(Account.State.CONFIRMED);
                accountRepository.save(account);
                return "successful_registration";
            }
        }
        return "error";
    }

    @Override
    public boolean emailExists(String email) {
        return accountRepository.findAccountByEmail(email).isPresent();
    }

    @Override
    public boolean passwordValid(String password) {

        //At least 1 digit, 1 lower case letter, 1 upper case letter, no whitespace, minimum 8 letters

        return Pattern.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", password);
    }


    @Override
    public boolean isAuth(SignInDto signInDto) {
        Account account = accountRepository.findByEmail(signInDto.getEmail());
        if (account != null) {
            return account.getPassword().equals(signInDto.getPassword());
        }
        return false;
    }


    @Override
    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public boolean checkConfirm(String email) {
        Account account = accountRepository.findByEmail(email);
        return account.getState().equals(Account.State.CONFIRMED);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

}
