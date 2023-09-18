package org.example.controllers;

import org.example.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public String get(Model model){
//        model.addAttribute("param", testRepository.findAccountByName("name1"));
//        model.addAttribute("param", testRepository.findByUserName("name1").toString());
//        System.out.println(String.valueOf(testRepository.getIdByStrcode("name2")));
        System.out.println(testRepository.countUserApplications("email1"));
        System.out.println(testRepository.findAllWithAccount());
        System.out.println(testRepository.findAllWithAccountTest());
        System.out.println(testRepository.getNotesByAccountId(1L));
        System.out.println(testRepository.getAccountById(1L));
        return "hello";
    }
}
