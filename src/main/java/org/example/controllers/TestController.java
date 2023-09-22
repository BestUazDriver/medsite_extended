package org.example.controllers;

import org.example.models.UserActivity;
import org.example.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public String get(Model model){
//        model.addAttribute("param", testRepository.findAccountByName("name1"));
//        model.addAttribute("param", testRepository.findByUserName("name1").toString());
//        System.out.println(String.valueOf(testRepository.getIdByStrcode("name2")));
//        System.out.println(testRepository.countUserApplications("email1"));
//        System.out.println(testRepository.findAllWithAccount());
//        System.out.println(testRepository.findAllWithAccountTest());
//        System.out.println(testRepository.getAccountById(1L));
//        System.out.println(testRepository.findByApplicationIdAndFeatureId(1L, 1L));
        System.out.println(testRepository.findByApplicationIdAndFeatureId(1L, 1L).toString());
        System.out.println(testRepository.findByApplicationId(1L));
        System.out.println(testRepository.findByApplicationIdOld(1L));
        System.out.println(testRepository.findByFeatureIdOld(1L));
        System.out.println(testRepository.findApplicationFeaturesOld(1L, 1L));
        System.out.println(testRepository.findApplicationFeaturesNew(1L, 1L));
        List<Long> objects = new ArrayList<>();
        objects.add(2L);
        objects.add(1L);
        System.out.println(testRepository.findByFeatureIdNew(objects));
        System.out.println(testRepository.exists(1L, "avatar", 1L, Arrays.asList(UserActivity.email1, UserActivity.email2)));
        System.out.println(testRepository.countUserBookmarks(2L));
        return "hello";
    }
}
