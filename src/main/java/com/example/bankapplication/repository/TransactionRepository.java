package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
  Optional<Transaction> findById(UUID id);
  void deleteById(UUID id);
}
