package com.sabitov.repositories;

import com.sabitov.models.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinetRepository extends JpaRepository<Cabinet, Long> {

    List<Cabinet> findAllByOffice(String office);
}
