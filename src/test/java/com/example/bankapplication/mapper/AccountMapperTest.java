package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.EntityCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account mapper test class")
class AccountMapperTest {
  private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

  @DisplayName("Positive test. Account mapper to DTO test")
  @Test
  void shouldConvertEntityToDtoTest() {
    // given
    Account account = EntityCreator.getAccount(UUID.randomUUID());

    //when
    AccountDto actual = accountMapper.toDto(account);

    //then
    assertEquals(account.getId().toString(), actual.getId());
    assertEquals(account.getName(), actual.getName());
    assertEquals(account.getType().toString(), actual.getType());
    assertEquals(account.getStatus().toString(), actual.getStatus());
    assertEquals(account.getBalance().toString(), actual.getBalance());
  }

  @DisplayName("Positive test. Account (with null field) mapper to DTO test")
  @Test
  void shouldWithNullValueConvertEntityToDtoTest() {
    // given
    Account account = EntityCreator.getAccount(UUID.randomUUID());
    account.setId(null);
    account.setType(null);
    account.setStatus(null);
    account.setBalance(null);

    //when
    AccountDto actual = accountMapper.toDto(account);

    //then
    assertNull(actual.getId());
    assertNull(actual.getType());
    assertNull(account.getStatus());
    assertNull(account.getBalance());
  }

  @DisplayName("Negative test. Null mapper to DTO test")
  @Test
  void shouldReturnNullWhileConvertNullEntityToDtoTest() {
    assertNull(accountMapper.toDto(null));
  }

  @DisplayName("Positive test. Account mapper to DTO list test")
  @Test
  void shouldConvertEntityToDtoListTest() {
    // given
    Account account = EntityCreator.getAccount(UUID.randomUUID());
    List<Account> accounts = List.of(account);

    //when
    List<AccountDto> accountDtoList = accountMapper.toDtoList(accounts);

    //then
    assertEquals(1, accountDtoList.size());
    assertEquals(account.getId().toString(), accountDtoList.get(0).getId());
  }

  @DisplayName("Negative test. Null mapper to DTO list test")
  @Test
  void shouldReturnNullWhileConvertEntityToDtoListTest() {
    assertNull(accountMapper.toDtoList(null));
  }
}