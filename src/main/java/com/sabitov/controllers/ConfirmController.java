package com.sabitov.controllers;

import com.sabitov.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/confirm")
@RequiredArgsConstructor
public class ConfirmController {

    private final AccountService accountService;

    @GetMapping("/{confirmation-code}")
    public String confirm(@PathVariable("confirmation-code") String code) {
        return accountService.confirmAccount(code);
    }
}
