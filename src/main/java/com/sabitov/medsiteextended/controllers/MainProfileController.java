package com.sabitov.medsiteextended.controllers;

import com.sabitov.medsiteextended.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class MainProfileController {

    @GetMapping
    public String main(HttpServletRequest request, Model model) {
        Account account = (Account) request.getSession().getAttribute("account");
        model.addAttribute("name", account.getName());
        return "profile";
    }

}
