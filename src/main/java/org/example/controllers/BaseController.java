package org.example.controllers;

import org.example.config.StaticResourceMessageSourceAccessor;
import org.example.services.BaseService;
import org.example.services.StaticMethodsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/test")
public class BaseController {

    @Autowired
    private ResourceBundleMessageSource messageSource;
    @Autowired
    public BaseService baseService;

    @GetMapping
    @RequestMapping("/get")
    public String getBaseView(Model model){
        model.addAttribute("message", messageSource.getMessage("hello", null, new Locale("ja_JP")));
        model.addAttribute("lol", baseService.getCurrentLocale());

        System.out.println(StaticMethodsClass.getBean());
        return "articles";
    }
}
