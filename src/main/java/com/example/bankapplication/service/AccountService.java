package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.enums.AccountStatus;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {
  AccountDto createAccount(AccountDto accountDto);

  AccountDto findById(UUID id);

  List<AccountDto> getAllByStatus(AccountStatus status);

  BigDecimal getAccountBalance(UUID id);
}
