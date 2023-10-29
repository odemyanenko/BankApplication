package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.service.ClientService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static util.DtoCreator.getClientDto;

@DisplayName("Client controller test class")
@WebMvcTest(ClientController.class)
class ClientControllerTest {

  @MockBean
  private ClientService clientService;

  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Client by Id")
  @Test
  void getClientById() throws Exception {
    //given
    ClientDto clientDto = getClientDto();
    UUID clientId = UUID.fromString(clientDto.getId());

    //when
    Mockito.when(clientService.findById(clientId)).thenReturn(clientDto);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/clients/{id}", clientId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(JsonUtil.asJsonString(clientDto)));
  }

  @DisplayName("Negative test. Get Client by Id. Not Found")
  @Test
  void getClientByNotExistIdWithExceptionTest() throws Exception {
    //given
    UUID noExistsClientId = UUID.randomUUID();

    //when
    Mockito.when(clientService.findById(noExistsClientId))
            .thenThrow(new ResourceNotFoundException(ErrorMessage.CLIENT_NOT_FOUND));

    //then
    mockMvc.perform(MockMvcRequestBuilders.get("/auth/clients/{id}", noExistsClientId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.CLIENT_NOT_FOUND));
  }

}