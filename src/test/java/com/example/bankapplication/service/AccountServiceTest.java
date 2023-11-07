package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.exception.ResourceListEmptyException;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.AccountMapper;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DtoCreator;
import util.EntityCreator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@DisplayName("Account service test class")
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
  @InjectMocks
  public AccountServiceImpl accountService;
  @Mock
  public AccountRepository accountRepository;
  @Mock
  private AccountMapper accountMapper;

  private Account account;
  private AccountDto accountDto;

  @BeforeEach
  void setUp() {
    UUID id = UUID.randomUUID();
    account = EntityCreator.getAccount(id);
    accountDto = DtoCreator.getAccountDto(id);
  }

  @DisplayName("Positive test. Get Account by Id")
  @Test
  void findByIdTest() {
    //given
    when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.of(account));
    when(accountMapper.toDto(account)).thenReturn(accountDto);

    //when
    AccountDto findAccountDto = accountService.findById(account.getId());

    //then
    Assertions.assertEquals(account.getId().toString(), findAccountDto.getId());
    Assertions.assertEquals(account.getName(), findAccountDto.getName());
  }

  @DisplayName("Negative test. Get Account by Id. Get Resource Not Found Exception")
  @Test
  void findByIdWithResourceNotFoundExceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> accountService.findById(null));
  }

  @DisplayName("Positive test. Get Account list by status")
  @Test
  void getAllByStatusTest() {
    //given
    List<Account> accounts = List.of(account);
    List<AccountDto> accountDtoList = List.of(accountDto);
    when(accountRepository.getAllByStatus(AccountStatus.ACTIVE)).thenReturn(accounts);
    when(accountMapper.toDtoList(accounts)).thenReturn(accountDtoList);

    //when
    List<AccountDto> findAccounts = accountService.getAllByStatus(AccountStatus.ACTIVE);

    //then
    Assertions.assertEquals(1, findAccounts.size());
  }

  @DisplayName("Negative test. Get Account list by status. Get Resource List Empty Exception")
  @Test
  void getAllByStatusWithResourceListEmptyExceptionTest() {
    Assertions.assertThrows(ResourceListEmptyException.class, () -> accountService.getAllByStatus(null));
  }
}