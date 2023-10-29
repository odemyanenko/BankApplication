package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceListEmptyException;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.AccountMapper;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  @Override
  public AccountDto findById(UUID id) {
    Optional<Account> accountOptional = accountRepository.findById(id);
    Account account = accountOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

    return accountMapper.toDto(account);
  }

  @Override
  public List<AccountDto> getAllByStatus(AccountStatus status) {
    List<Account> accounts = accountRepository.getAllByStatus(status);
    if (accounts.isEmpty()) {
      throw new ResourceListEmptyException(ErrorMessage.ACCOUNT_LIST_EMPTY);
    }
    return accountMapper.toDtoList(accounts);
  }
}
