package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.entity.Transaction;
import com.example.bankapplication.mapper.TransactionMapper;
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
  private final TransactionMapper transactionMapper;

  @Override
  public Optional<TransactionDto> findById(UUID id) {
    Optional<Transaction> transactionOptional = transactionRepository.findById(id);
    if (transactionOptional.isPresent()) {
      TransactionDto transactionDto = transactionMapper.toDto(transactionOptional.get());
      return Optional.of(transactionDto);
    } else {
      return Optional.empty();
    }
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
