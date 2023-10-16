package com.example.bankapplication.controller;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("auth/transactions")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @GetMapping("/{id}")
  public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") UUID id) {
    TransactionDto transactionDto = transactionService.findById(id);
    return ResponseEntity.ok(transactionDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransactionById(@PathVariable("id") UUID id) {
    boolean transactionDeleted = transactionService.deleteById(id);

    if (transactionDeleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
