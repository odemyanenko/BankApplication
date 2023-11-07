package com.example.bankapplication.service;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.entity.Transaction;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.TransactionMapper;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.service.impl.TransactionalServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DtoCreator;
import util.EntityCreator;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Transaction service test class")
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
  @InjectMocks
  public TransactionalServiceImpl transactionService;
  @Mock
  public TransactionRepository transactionRepository;
  @Mock
  private TransactionMapper transactionMapper;

  private Transaction transaction;
  private TransactionDto transactionDto;

  @BeforeEach
  void setUp() {
    UUID id = UUID.randomUUID();
    transaction = EntityCreator.getTransaction(id);
    transactionDto = DtoCreator.getTransactionDto(id);
  }

  @DisplayName("Positive test. Get Transaction by Id.")
  @Test
  void findByIdTest() {
    //given
    when(transactionRepository.findById(any(UUID.class))).thenReturn(Optional.of(transaction));
    when(transactionMapper.toDto(transaction)).thenReturn(transactionDto);

    //when
    TransactionDto findTransactionDto = transactionService.findById(transaction.getId());

    //then
    Assertions.assertEquals(transaction.getId().toString(), findTransactionDto.getId());
    Assertions.assertEquals(transaction.getCreditAccount().getName(), findTransactionDto.getCreditAccountName());
    Assertions.assertEquals(transaction.getCreditAccount().getName(), findTransactionDto.getCreditAccountName());
  }

  @DisplayName("Positive test. Delete Transaction by Id.")
  @Test
  void deleteByIdTest() {
    //given
    UUID deletedId = UUID.randomUUID();
    when(transactionRepository.existsById(deletedId)).thenReturn(true);

    //when
    boolean isDeleted = transactionService.deleteById(deletedId);

    //then
    Assertions.assertTrue(isDeleted);
    verify(transactionRepository).deleteById(deletedId);
  }

  @DisplayName("Negative test. Delete Transaction by Unknown Id.")
  @Test
  void deleteByIdWithUnknownIdTest() {
    //given
    when(transactionRepository.existsById(any(UUID.class))).thenReturn(false);

    //when
    boolean isDeleted = transactionService.deleteById(UUID.randomUUID());

    //then
    Assertions.assertFalse(isDeleted);
  }

  @DisplayName("Negative test. Get Transaction by Id. Get Resource Not Found Exception")
  @Test
  void findByIdWithResourceNotFoundExceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> transactionService.findById(null));
  }
}