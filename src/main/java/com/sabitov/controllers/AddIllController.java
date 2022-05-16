package com.sabitov.controllers;

import com.sabitov.models.Ill;
import com.sabitov.services.IllService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/add_ill")
@RequiredArgsConstructor
public class AddIllController {

    private final IllService illService;

    @GetMapping
    public String getAvailableIlls(Model model){
        model.addAttribute("illness", illService.findAll());
        return "add_ill";
    }

    @RequestMapping(value = "/search/name", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Ill> getIllsByName(@RequestParam("name") String name) {
        return illService.searchExistsEmail(name);
    }


}
