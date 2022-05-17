package com.sabitov.controllers;

import com.sabitov.models.Account;
import com.sabitov.services.AccountService;
import com.sabitov.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/assigned_notes")
public class AssignedNotesController {

    private final AccountService accountService;
    private final NoteService noteService;

    @GetMapping
    public String getNotes(Model model, Principal principal){
        Account account = accountService.findAccountByEmail(principal.getName()).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("notes", account.getDocNotes());
        return "assigned_notes";
    }

    @GetMapping("/start/{note_id}")
    public String getStarted(@PathVariable String note_id, HttpServletRequest request){
        request.getSession().setAttribute("patient", noteService.findById(Integer.parseInt(note_id)).getPatient());
        request.getSession().setAttribute("note", noteService.findById(Integer.parseInt(note_id)));
        System.err.println(noteService.findById(Integer.parseInt(note_id)).getPatient());
        return "redirect:/add_ill";
    }

}
