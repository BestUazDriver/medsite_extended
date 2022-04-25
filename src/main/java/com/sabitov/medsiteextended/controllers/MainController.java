package com.sabitov.medsiteextended.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String main(Authentication authentication){
        if (authentication != null) {
            return "redirect:/profile";
        }

        return "home";
    }
}
