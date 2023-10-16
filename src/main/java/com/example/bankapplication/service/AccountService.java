package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountDto;

import java.util.UUID;

public interface AccountService {
  AccountDto findById(UUID id);
}
