package com.sabitov.services;

import com.sabitov.models.Ill;
import com.sabitov.repositories.IllCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IllServiceImpl implements IllService{

    private final IllCriteriaRepository illRepository;

    @Override
    public List<Ill> findAll() {
        return illRepository.findAll();
    }

    @Override
    public List<Ill> searchExistsEmail(String name) {
        return illRepository.findAllByNameExists(name);
    }

    @Override
    public Ill findIllByName(String name) {
        return illRepository.findByName(name);
    }

    @Override
    @Transactional
    public void save(Ill ill) {
        illRepository.save(ill);
    }
}
