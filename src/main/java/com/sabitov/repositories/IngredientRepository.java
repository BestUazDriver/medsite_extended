package com.sabitov.repositories;

import com.sabitov.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Account, Long> {
}
