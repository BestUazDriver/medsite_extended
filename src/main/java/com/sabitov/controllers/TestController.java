package com.sabitov.controllers;

import com.sabitov.services.AccountService;
import com.sabitov.services.IllService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final IllService illRepository;
    private final AccountService accountService;

    @GetMapping
    public String get(){
        ArrayList<Object> objects = null;
        objects.get(1);
        return "home";
    }
}
