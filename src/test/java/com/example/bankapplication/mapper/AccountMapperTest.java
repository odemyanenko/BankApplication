package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.EntityCreator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account mapper test class")
class AccountMapperTest {
  private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

  @DisplayName("Positive test. Account mapper to DTO test")
  @Test
  void shouldConvertEntityToDtoTest() {
    // given
    Account account = EntityCreator.getAccount();

    //when
    AccountDto actual = accountMapper.toDto(account);

    //then
    assertEquals(account.getId().toString(), actual.getId());
    assertEquals(account.getName(), actual.getName());
    assertEquals(account.getType().toString(), actual.getType());
    assertEquals(account.getStatus().toString(), actual.getStatus());
    assertEquals(account.getBalance().toString(), actual.getBalance());
  }

  @DisplayName("Negative test. Null mapper to DTO test")
  @Test
  void shouldReturnNullWhileConvertNullEntityToDtoTest() {
    assertNull(accountMapper.toDto(null));
  }

}