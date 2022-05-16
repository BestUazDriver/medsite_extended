package com.sabitov.services;

import com.sabitov.dto.SignInDto;
import com.sabitov.dto.SignUpDto;
import com.sabitov.models.Account;
import com.sabitov.models.Ill;
import com.sabitov.repositories.AccountRepository;
import com.sabitov.repositories.IllCriteriaRepository;
import com.sabitov.utils.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final IllCriteriaRepository illRepository;

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

    @Override
    public List<Ill> findAllIllnessByAccountId(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void addIll(Ill ill, Account account) {
        if(account.getIllness() == null){
            List<Ill> illness = new ArrayList<>();
            illness.add(ill);
            account.setIllness(illness);
        }else{
            account.getIllness().add(ill);
        }
        accountRepository.save(account);
        if (ill.getAccounts() == null){
            List<Account> accounts = new ArrayList<>();
            accounts.add(account);
            ill.setAccounts(accounts);
        }else{
            ill.getAccounts().add(account);
        }
        illRepository.save(ill);
    }
}
