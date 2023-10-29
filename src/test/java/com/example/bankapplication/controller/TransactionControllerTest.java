package com.example.bankapplication.controller;

import com.example.bankapplication.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Transaction controller test class")
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

  @MockBean
  private TransactionService transactionService;

  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Transaction by Id")
  @Test
  void getTransactionById() {
  }

  @DisplayName("Negative test. Get Transaction by Id. Not Found")
  @Test
  void getTransactionByNotExistIdWithExceptionTest(){
  }
}