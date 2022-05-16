package com.sabitov.controllers;

import com.sabitov.models.Account;
import com.sabitov.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class MainProfileController {

    @GetMapping
    public String main(HttpServletRequest request, Model model) {
        Account account = (Account) request.getSession().getAttribute("account");
        model.addAttribute("name", account.getName());
        model.addAttribute("illness", account.getIllness());
        model.addAttribute("role", account.getRole().name());
        return "profile";
    }

}
