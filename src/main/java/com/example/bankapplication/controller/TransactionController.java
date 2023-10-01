package com.example.bankapplication.controller;

import com.example.bankapplication.entity.Transaction;
import com.example.bankapplication.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/transactions")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @GetMapping("/{id}")
  public ResponseEntity<Transaction> getTransactionId(@PathVariable("id") UUID id) {
    Optional<Transaction> transactionOptional = transactionService.findById(id);

    if (transactionOptional.isPresent()) {
      return new ResponseEntity<>(transactionOptional.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransactionId(@PathVariable("id") UUID id) {
    boolean transactionDeleted = transactionService.deleteById(id);

    if (transactionDeleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
