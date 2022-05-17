package com.sabitov.controllers;

import com.sabitov.models.Account;
import com.sabitov.models.Ill;
import com.sabitov.models.Note;
import com.sabitov.services.AccountService;
import com.sabitov.services.IllService;
import com.sabitov.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/add_ill")
@RequiredArgsConstructor
public class AddIllController {

    private final IllService illService;
    private final AccountService accountService;
    private final NoteService noteService;

    @GetMapping
    public String getAvailableIlls(Model model, HttpServletRequest request) {
        model.addAttribute("illness", illService.findAll());
        Account account = (Account) request.getSession().getAttribute("patient");
        if (account != null) {
            model.addAttribute("patient_illness", account.getIllness());
        }
        return "add_ill";
    }

    @RequestMapping(value = "/search/name", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Ill> getIllsByName(@RequestParam("name") String name) {
        return illService.searchExistsEmail(name);
    }

    @PostMapping("/finish")
    public String finishAppointment(HttpServletRequest request, Principal principal) {
        Account account = (Account) request.getSession().getAttribute("patient");
        Account doctor = accountService.findAccountByEmail(principal.getName()).orElseThrow(IllegalArgumentException::new);
        Note note = (Note) request.getSession().getAttribute("note");
        request.getSession().removeAttribute("note");
        request.getSession().removeAttribute("patient");
        noteService.delete(note.getId());
        System.out.println("finished appointment");
        return "redirect:/profile";
    }
}
