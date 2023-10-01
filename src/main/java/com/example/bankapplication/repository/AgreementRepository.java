package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
  Optional<Agreement> findById(UUID id);
}
