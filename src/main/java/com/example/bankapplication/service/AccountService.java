package com.example.bankapplication.service;

import com.example.bankapplication.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountService {
  Optional<Account> findById(UUID id);
}
