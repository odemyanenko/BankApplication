package com.example.bankapplication.repository;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {
  Optional<Manager> findById(UUID id);
  Manager save(Manager manager);
}
