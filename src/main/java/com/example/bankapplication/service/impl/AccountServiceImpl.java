package com.example.bankapplication.service.impl;

import com.example.bankapplication.entity.Account;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  public Optional<Account> getAccountByID(UUID id) {
    return accountRepository.getAccountById(id);
  }
}
