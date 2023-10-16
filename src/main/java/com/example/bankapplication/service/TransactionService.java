package com.example.bankapplication.service;

import com.example.bankapplication.dto.TransactionDto;

import java.util.UUID;

public interface TransactionService {
  TransactionDto findById(UUID id);
  boolean deleteById(UUID id);
}
