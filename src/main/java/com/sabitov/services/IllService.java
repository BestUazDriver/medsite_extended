package com.sabitov.services;

import com.sabitov.models.Ill;

import java.util.List;

public interface IllService {

    List<Ill> findAll();

    List<Ill> searchExistsEmail(String name);

    Ill findIllByName(String name);

    void save(Ill ill);
}
