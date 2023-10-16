package com.example.bankapplication.controller;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("auth/accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @GetMapping("/{id}")
  public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") UUID id) {
    AccountDto accountDto = accountService.findById(id);
    return ResponseEntity.ok(accountDto);
  }
}
