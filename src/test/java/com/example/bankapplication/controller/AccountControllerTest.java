package com.example.bankapplication.controller;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceListEmptyException;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.AccountMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.bankapplication.service.AccountService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.JsonUtil;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.DtoCreator.getAccountDto;

@DisplayName("Account controller test class")
@WebMvcTest(AccountController.class)
class AccountControllerTest {
  @MockBean
  private AccountService accountService;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Account by Id")
  @Test
  void getAccountByIdTest() throws Exception {
    //given
    AccountDto accountDto = getAccountDto(UUID.randomUUID());
    UUID accountId = UUID.fromString(accountDto.getId());

    //when
    Mockito.when(accountService.findById(accountId)).thenReturn(accountDto);
    MvcResult accountGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/{id}", accountId))
            .andReturn();

    //then
    Assertions.assertEquals(HttpStatus.OK.value(), accountGetResult.getResponse().getStatus());

    String accountGetStringJson = accountGetResult.getResponse().getContentAsString();
    AccountDto receivedAccountJson = objectMapper.readValue(accountGetStringJson, AccountDto.class);

    Assertions.assertEquals(accountDto, receivedAccountJson);
  }

  @DisplayName("Positive test. Get Account list by status")
  @Test
  void getAllByStatusTest() throws Exception {
    //given
    AccountDto accountDto = getAccountDto(UUID.randomUUID());
    AccountStatus status = AccountStatus.ACTIVE;
    List<AccountDto> accountDtoList = Collections.singletonList(accountDto);

    //when
    Mockito.when(accountService.getAllByStatus(status)).thenReturn(accountDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/all/{status}", status))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(JsonUtil.asJsonString(accountDtoList)));
    Mockito.verify(accountService, Mockito.times(1)).getAllByStatus(status);
  }

  @DisplayName("Negative test. Get Account by Id. Not Found")
  @Test
  void getAccountByNotExistIdWithExceptionTest() throws Exception {
    //given
    UUID noExistsAccountId = UUID.randomUUID();

    //when
    Mockito.when(accountService.findById(noExistsAccountId))
            .thenThrow(new ResourceNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/{id}", noExistsAccountId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.ACCOUNT_NOT_FOUND));
  }

  @DisplayName("Negative test. Get Account list by status. Empty list")
  @Test
  void getAccountEmptyListByStatusWithExceptionTest() throws Exception {
    //given
    AccountDto accountDto = getAccountDto(UUID.randomUUID());
    AccountStatus status = AccountStatus.INACTIVE;
    List<AccountDto> accountDtoList = Collections.singletonList(accountDto);

    //when
    Mockito.when(accountService.getAllByStatus(status))
            .thenThrow(new ResourceListEmptyException(ErrorMessage.ACCOUNT_LIST_EMPTY));

    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/all/{status}", status))
            .andExpect(status().isNoContent())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NO_CONTENT.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.ACCOUNT_LIST_EMPTY));
  }

  @DisplayName("Negative test. Get Account list by status. Unknown status")
  @Test
  void getAccountListByNotExistStatusWithExceptionTest() throws Exception {
    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/accounts/all/unknown"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()));
  }

}