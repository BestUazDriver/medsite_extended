package com.sabitov.controllers;

import com.sabitov.dto.SignUpDto;
import com.sabitov.services.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sign_up")
public class SignUpController {

    private final AccountServiceImpl accountService;
    private String error = "";

    @GetMapping
    public String main(Model model, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        model.addAttribute("error_message", error);
        return "home";
    }

    @PostMapping
    public String signUp(SignUpDto signUpDto) {
        if (accountService.emailExists(signUpDto.getEmail())) {
            error = "Email already exists";
            return "redirect:/sign_up?err";
        }
        if (!accountService.passwordValid(signUpDto.getPassword())) {
            error = "Password should have at least 1 digit, 1 lower case letter, 1 upper case letter, no whitespace, minimum 8 letters";
            return "redirect:/sign_up?err";
        }
        try {
            accountService.signUp(signUpDto);
        }catch (NullPointerException e){
            error = "Fill all fields";
            return "redirect:/sign_up?err";
        }
        return "confirmWaiting";
    }
}

