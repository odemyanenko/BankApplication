package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Client;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceListEmptyException;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.AccountMapper;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.ClientRepository;
import com.example.bankapplication.security.UserProvider;
import com.example.bankapplication.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final UserProvider userProvider;
  private final ClientRepository clientRepository;

  @Override
  @Transactional
  public AccountDto createAccount(AccountDto accountDto){
    Account account = accountMapper.toAccountEntity(accountDto);
    Optional<Client> clientOptional = clientRepository.findByEmail(userProvider.getCurrentUser().getEmail());
    Client client = clientOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.CLIENT_NOT_FOUND));
    account.setClient(client);
    accountRepository.save(account);
    return accountMapper.toDto(account);
  }

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

  @Override
  public BigDecimal getAccountBalance(UUID id) {
    return accountRepository.getAccountBalance(id);
  }
}
