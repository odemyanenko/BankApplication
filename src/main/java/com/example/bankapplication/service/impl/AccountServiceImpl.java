package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Client;
import com.example.bankapplication.mapper.AccountMapper;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  @Override
  public Optional<AccountDto> findById(UUID id) {
    Optional<Account> accountOptional = accountRepository.findById(id);
    if (accountOptional.isPresent()){
      AccountDto accountDto = accountMapper.toDto(accountOptional.get());
      return Optional.of(accountDto);
    } else{
      return Optional.empty();
    }
  }
}
