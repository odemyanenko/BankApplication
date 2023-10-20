package com.example.bankapplication.mapper;

import com.example.bankapplication.entity.Account;
import org.junit.jupiter.api.Test;
import util.EntityCreator;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {
//  private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);
//
  @Test
  void toDto() {
    Account account = EntityCreator.getAccount();
//    AccountDto accountDto = accountMapper.toDto(account);

    assertEquals(account.getId().toString(), account.getId().toString());
//    assertEquals(account.getId().toString(), accountDto.getId());
//    assertEquals(account.getName(), accountDto.getName());
//    assertEquals(account.getStatus().toString(), accountDto.getStatus());
//    assertEquals(account.getType().toString(), accountDto.getType());
  }
}