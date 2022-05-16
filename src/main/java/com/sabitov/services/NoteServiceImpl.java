package com.sabitov.services;

import com.sabitov.dto.NoteDto;
import com.sabitov.models.Account;
import com.sabitov.models.Cabinet;
import com.sabitov.models.Note;
import com.sabitov.repositories.AccountRepository;
import com.sabitov.repositories.CabinetRepository;
import com.sabitov.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final AccountRepository accountRepository;
    private final CabinetRepository cabinetRepository;

    @Override
    @Transactional
    public void save(NoteDto noteDto) {
        Note note = NoteDto.from(noteDto);
        List<Cabinet> cabinetsByOffice = cabinetRepository.findAllByOffice(noteDto.getAddress());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findAccountByEmail(authentication.getName()).orElseThrow(IllegalArgumentException::new);

        note.setPatient(account);
        note.setOffice(cabinetsByOffice.get(new Random().nextInt(cabinetsByOffice.size())).getNumber());
        noteRepository.save(note);

        if (account.getNotes() == null) {
            List<Note> notes = new ArrayList<>();
            notes.add(note);
            account.setNotes(notes);
        } else {
            account.getNotes().add(note);
        }
        accountRepository.save(account);
    }

    @Override
    public Optional<List<Note>> findNotesByAddress(String address) {
        return Optional.ofNullable(noteRepository.findAllByAddress(address));
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public void assign(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findAccountByEmail(authentication.getName()).orElseThrow(IllegalArgumentException::new);
        Note note = noteRepository.findById((long) id).orElseThrow(IllegalArgumentException::new);

        if (account.getDocNotes() == null) {
            account.setNotes(new ArrayList<>());
        }
        account.getDocNotes().add(note);
        note.setDoctor(account);
        accountRepository.save(account);
        noteRepository.save(note);
    }

    @Override
    public Note findById(int noteId) {
        return noteRepository.findById((long) noteId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Note> findAllWithoutAssigners() {
        return findAll().stream().filter(note -> note.getDoctor() == null).collect(Collectors.toList());
    }
}
