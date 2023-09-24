package com.example.bankapplication.controller;

import com.example.bankapplication.entity.Account;
import com.example.bankapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @GetMapping("/{id}")
  public Optional<Account> getAccountById(@PathVariable("id") UUID id){
    return accountService.getAccountByID(id);
  }
}
