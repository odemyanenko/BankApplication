package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.enums.AccountStatus;

import java.util.List;
import java.util.UUID;

public interface AccountService {
  AccountDto findById(UUID id);

  List<AccountDto> getAllByStatus(AccountStatus status);
}
