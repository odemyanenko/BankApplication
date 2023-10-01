package com.example.bankapplication.service.impl;

import com.example.bankapplication.entity.Transaction;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionalServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;

  @Override
  public Optional<Transaction> findById(UUID id) {
    return transactionRepository.findById(id);
  }

  @Override
  public boolean deleteById(UUID id) {
    if (transactionRepository.existsById(id)) {
      transactionRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
