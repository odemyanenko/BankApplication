package com.example.bankapplication.service;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionService {
  Optional<TransactionDto> findById(UUID id);
  boolean deleteById(UUID id);
}
