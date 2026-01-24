package com.app.SportsFieldManagement.repository;

import com.app.SportsFieldManagement.model.SportField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SportFieldRepository extends JpaRepository<SportField,Long> {
    Optional<SportField> findById(Long id);
    Optional<SportField> findBySportType(String name);
    List<SportField> findAll();
}
