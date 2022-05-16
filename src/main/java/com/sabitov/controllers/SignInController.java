package com.sabitov.controllers;

import com.sabitov.models.Account;
import com.sabitov.security.details.AccountUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sign_in")
public class SignInController {

    @GetMapping
    public String main(Model model, Authentication authentication, HttpServletRequest request) {
        if (authentication != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AccountUserDetails accountUserDetails = (AccountUserDetails) principal;
            Account account = accountUserDetails.getAccount();
            System.out.println(account);
            request.getSession(false).setAttribute("account", account);
            return "redirect:/profile";
        }
        model.addAttribute("sign_in_error_message", "Some issues with your account");
        return "home";
    }

}


