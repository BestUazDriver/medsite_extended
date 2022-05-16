package com.sabitov.controllers;

import com.sabitov.dto.SignUpDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private String error="";

    @GetMapping
    public String main(Authentication authentication, Model model){
        model.addAttribute("signUpDto", new SignUpDto());
        model.addAttribute("error_message", error);
        if (authentication != null) {
            return "redirect:/profile";
        }

        return "home";
    }
}
