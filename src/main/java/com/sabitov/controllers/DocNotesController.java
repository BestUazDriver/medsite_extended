package com.sabitov.controllers;

import com.sabitov.services.NoteService;
import com.sabitov.utils.OfficeFileParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/doc_notes")
public class DocNotesController {

    private final NoteService noteService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("notes", noteService.findAllWithoutAssigners());
        model.addAttribute("addresses", new OfficeFileParser().getOffices());
        return "doc_notes";
    }

    @PostMapping
    public String getFilteredNotes(@RequestParam("note_id") String noteId){
        int id = Integer.parseInt(noteId);
        noteService.assign(id);
        return "redirect:/profile";
    }
}
