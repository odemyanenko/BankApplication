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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.DtoCreator.getAgreementDto;

@DisplayName("Agreement controller test class")
@WebMvcTest(AgreementController.class)
class AgreementControllerTest {
  private final String URL_BASE = "/auth/agreements";
  @MockBean
  private AgreementService agreementService;
  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Agreement by Id")
  @Test
  void getAgreementById() throws Exception {
    //given
    UUID agreementId = UUID.randomUUID();
    AgreementDto agreementDto = getAgreementDto(agreementId);

    //when
    Mockito.when(agreementService.findById(agreementId)).thenReturn(agreementDto);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", agreementId))
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
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", noExistsAgreementId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.AGREEMENT_NOT_FOUND));
  }

  @DisplayName("Positive test. Get Agreements by ManagerId")
  @Test
  void getAgreementByProductManagerIdTest() throws Exception {
    //given
    UUID managerId = UUID.randomUUID();
    AgreementDto agreementDto = getAgreementDto(UUID.randomUUID());
    List<AgreementDto> agreementDtoList = Collections.singletonList(agreementDto);

    //when
    Mockito.when(agreementService.findByProductManagerId(managerId)).thenReturn(agreementDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all-manager/{id}", managerId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(JsonUtil.asJsonString(agreementDtoList)));
    Mockito.verify(agreementService, Mockito.times(1)).findByProductManagerId(managerId);
  }

  @DisplayName("Negative test. Get Agreements by ManagerId. Empty List")
  @Test
  void getProductEmptyListTest() throws Exception {
    //given
    UUID managerId = UUID.randomUUID();
    List<AgreementDto> agreementEmptyDtoList = new ArrayList<>();

    //when
    Mockito.when(agreementService.findByProductManagerId(managerId))
            .thenReturn(agreementEmptyDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all-manager/{id}", managerId))
            .andExpect(status().isNoContent());
    Mockito.verify(agreementService, Mockito.times(1)).findByProductManagerId(managerId);
  }

}