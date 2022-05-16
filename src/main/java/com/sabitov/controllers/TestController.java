package com.sabitov.controllers;

import com.sabitov.models.Ill;
import com.sabitov.repositories.IllCriteriaRepository;
import com.sabitov.services.AccountService;
import com.sabitov.services.IllService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final IllService illRepository;
    private final AccountService accountService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Ill> getIllsByName() {
        Ill ill = Ill.builder().
                name("gonoreya").build();
        return illRepository.findAll();
    }
}
