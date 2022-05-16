package com.sabitov.repositories;

import com.sabitov.models.Account;
import com.sabitov.models.Cure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CureRepository extends JpaRepository<Account, Long> {

}
