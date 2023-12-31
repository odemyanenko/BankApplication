package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Client;
import com.example.bankapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
  Optional<Client> findById(UUID id);
  Optional<Client> findByEmail(String email);
}
