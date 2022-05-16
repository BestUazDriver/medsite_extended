package com.sabitov.controllers;

import com.sabitov.dto.NoteDto;
import com.sabitov.services.NoteService;
import com.sabitov.services.CabinetService;
import com.sabitov.utils.OfficeFileParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/offer_note")
public class OfferNoteController {

    private final NoteService noteService;

    @GetMapping
    public String getPage(Model model){
        model.addAttribute("offices", new OfficeFileParser().getOffices());
//        model.addAttribute("cabinets", cabinetService.);
        return "offer_note";
    }

    @PostMapping
    public String getNote(NoteDto noteDto){
        noteService.save(noteDto);
        return "redirect:/offer_note";
    }
}
