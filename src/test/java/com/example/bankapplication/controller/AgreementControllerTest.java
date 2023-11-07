package com.example.bankapplication.controller;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.service.AgreementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.JsonUtil;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.DtoCreator.getAgreementDto;

@DisplayName("Agreement controller test class")
@WebMvcTest(AgreementController.class)
class AgreementControllerTest {
  @MockBean
  private AgreementService agreementService;
  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Agreement by Id")
  @Test
  void getAgreementById() throws Exception {
    //given
    AgreementDto agreementDto = getAgreementDto();
    UUID agreementId = UUID.fromString(agreementDto.getId());

    //when
    Mockito.when(agreementService.findById(agreementId)).thenReturn(agreementDto);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/agreements/{id}", agreementId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(JsonUtil.asJsonString(agreementDto)));
  }

  @DisplayName("Negative test. Get Agreement by Id. Not Found")
  @Test
  void getAgreementByNotExistIdWithExceptionTest() throws Exception {
    //given
    UUID noExistsAgreementId = UUID.randomUUID();

    //when
    Mockito.when(agreementService.findById(noExistsAgreementId))
            .thenThrow(new ResourceNotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND));

    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/agreements/{id}", noExistsAgreementId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.AGREEMENT_NOT_FOUND));
  }

  @DisplayName("Positive test. Get Agreement by Manager Id")
  @Test
  void getAgreementByProductManagerIdTest() {
  }
}