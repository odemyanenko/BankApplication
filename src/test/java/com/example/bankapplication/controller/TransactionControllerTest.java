package com.example.bankapplication.controller;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.DtoCreator.getTransactionDto;

@DisplayName("Transaction controller test class")
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
  private final String URL_BASE = "/auth/transactions";
  @MockBean
  private TransactionService transactionService;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Transaction by Id")
  @Test
  void getTransactionById() throws Exception {
    //given
    TransactionDto transactionDto = getTransactionDto(UUID.randomUUID());
    UUID transactionId = UUID.fromString(transactionDto.getId());

    //when
    Mockito.when(transactionService.findById(transactionId)).thenReturn(transactionDto);
    MvcResult transactionGetResult = mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", transactionId))
            .andReturn();
    //then
    Assertions.assertEquals(HttpStatus.OK.value(), transactionGetResult.getResponse().getStatus());
    String accountGetStringJson = transactionGetResult.getResponse().getContentAsString();
    TransactionDto receivedTransactionJson = objectMapper.readValue(accountGetStringJson, TransactionDto.class);

    Assertions.assertEquals(transactionDto, receivedTransactionJson);
  }

  @DisplayName("Negative test. Get Transaction by Id. Not Found")
  @Test
  void getTransactionByNotExistIdWithExceptionTest() throws Exception {
    //given
    UUID noExistsTransactionId = UUID.randomUUID();

    //when
    Mockito.when(transactionService.findById(noExistsTransactionId))
            .thenThrow(new ResourceNotFoundException(ErrorMessage.TRANSACTION_NOT_FOUND));

    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", noExistsTransactionId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.TRANSACTION_NOT_FOUND));
  }

  @DisplayName("Negative test. Delete Transaction with not exists id.")
  @Test
  void deleteTransactionWithNotFoundStatusTest() throws Exception {
    //given
    UUID transactionId = UUID.randomUUID();

    //when
    Mockito.when(transactionService.deleteById(transactionId)).thenReturn(false);

    //then
    mockMvc.perform(MockMvcRequestBuilders.delete(URL_BASE + "/{id}", transactionId))
            .andExpect(status().isNotFound());
  }

  @DisplayName("Positive test. Delete Transaction with exists id.")
  @Test
  void deleteTransactionWithNoContentStatusTest() throws Exception {
    //given
    UUID transactionId = UUID.randomUUID();

    //when
    Mockito.when(transactionService.deleteById(transactionId)).thenReturn(true);
//    willDoNothing().given(transactionService).delete(transactionId);

    //then
    mockMvc.perform(MockMvcRequestBuilders.delete(URL_BASE + "/{id}", transactionId))
            .andExpect(status().isNoContent());
  }

}