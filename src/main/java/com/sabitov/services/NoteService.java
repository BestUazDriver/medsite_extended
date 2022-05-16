package com.sabitov.services;

import com.sabitov.dto.NoteDto;
import com.sabitov.models.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    void save(NoteDto noteDto);

    Optional<List<Note>> findNotesByAddress(String address);

    List<Note> findAll();

    void assign(int id);

    Note findById(int parseInt);

    List<Note> findAllWithoutAssigners();
}
