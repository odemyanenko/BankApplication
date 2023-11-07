package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.entity.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.EntityCreator;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transaction mapper test class")
class TransactionMapperTest {
  private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

  @DisplayName("Positive test. Transaction mapper to DTO test")
  @Test
  void shouldConvertEntityToDtoTest() {
    // given
    Transaction expected = EntityCreator.getTransaction(UUID.randomUUID());

    //when
    TransactionDto actual = transactionMapper.toDto(expected);

    //then
    assertEquals(expected.getId().toString(), actual.getId());
    assertEquals(expected.getType().toString(), actual.getType());
    assertEquals(expected.getAmount().toString(), actual.getAmount());
    assertEquals(expected.getDescription(), actual.getDescription());
    assertEquals(expected.getStatus().toString(), actual.getStatus());
  }

  @DisplayName("Negative test. Null mapper to DTO test")
  @Test
  void shouldReturnNullWhileConvertNullEntityToDtoTest() {
    //then
    assertNull(transactionMapper.toDto(null));
  }

  @DisplayName("Negative test. Transaction (with null field) mapper to DTO test")
  @Test
  void shouldWithNullValueFieldsConvertEntityToDtoTest() {
    // given
    Transaction transaction = EntityCreator.getTransaction(UUID.randomUUID());
    transaction.setId(null);
    transaction.setType(null);
    transaction.setAmount(null);
    transaction.setStatus(null);

    //when
    TransactionDto actual = transactionMapper.toDto(transaction);

    //then
    assertNull(actual.getId());
    assertNull(actual.getType());
    assertNull(actual.getAmount());
    assertNull(actual.getStatus());
  }

  @DisplayName("Negative test. Transaction (with null accounts) mapper to DTO test")
  @Test
  void shouldWithNullValueAccountsConvertEntityToDtoTest() {
    // given
    Transaction transaction = EntityCreator.getTransaction(UUID.randomUUID());
    transaction.setDebitAccount(null);
    transaction.setCreditAccount(null);

    //when
    TransactionDto actual = transactionMapper.toDto(transaction);

    //then
    assertNull(actual.getDebitAccountName());
    assertNull(actual.getCreditAccountName());
  }

  @DisplayName("Negative test. Transaction (with null account names) mapper to DTO test")
  @Test
  void shouldWithNullAccountNamesConvertEntityToDtoTest() {
    // given
    Transaction transaction = EntityCreator.getTransaction(UUID.randomUUID());
    transaction.getDebitAccount().setName(null);
    transaction.getCreditAccount().setName(null);

    //when
    TransactionDto actual = transactionMapper.toDto(transaction);

    //then
    assertNull(actual.getDebitAccountName());
    assertNull(actual.getCreditAccountName());
  }

}