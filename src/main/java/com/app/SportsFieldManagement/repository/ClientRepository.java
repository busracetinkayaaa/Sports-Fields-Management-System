package com.app.SportsFieldManagement.repository;

import com.app.SportsFieldManagement.model.Client;
import com.app.SportsFieldManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByUser(User user);
    Optional<Client> findById(Long id);
    Optional<Client> findByPhone(String phone);
    List<Client> findAll();
    Optional<Client> findByUserId(Long userId);
    Optional<Client> findByUserUsername(String username);
}
