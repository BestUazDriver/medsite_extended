package com.sabitov.repositories;

import com.sabitov.models.Ill;

import java.util.List;

public interface IllCriteriaRepository {
    List<Ill> findAllByNameExists(String name);
    List<Ill> findAll();
    Ill findByName(String name);
    void save(Ill ill);
}
