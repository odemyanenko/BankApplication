package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.service.ManagerService;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.DtoCreator.getManagerDto;
import static util.EntityCreator.getManager;
import static util.JsonUtil.asJsonString;

@DisplayName("Manager controller test class")
@WebMvcTest(ManagerController.class)
class ManagerControllerTest {
  private final String URL_BASE = "/auth/managers";
  @MockBean
  private ManagerService managerService;
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Manager by Id")
  @Test
  void getManagerInfoById() throws Exception {
    //given
    ManagerDto managerDto = getManagerDto(UUID.randomUUID());
    UUID managerId = UUID.fromString(managerDto.getId());

    //when
    Mockito.when(managerService.findById(managerId)).thenReturn(managerDto);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", managerId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(asJsonString(managerDto)));
  }

  @DisplayName("Negative test. Get Manager by Id. Not Found")
  @Test
  void getManagerByNotExistIdWithExceptionTest() throws Exception {
    //given
    UUID noExistsManagerId = UUID.randomUUID();

    //when
    Mockito.when(managerService.findById(noExistsManagerId))
            .thenThrow(new ResourceNotFoundException(ErrorMessage.MANAGER_NOT_FOUND));

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", noExistsManagerId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.MANAGER_NOT_FOUND));
  }

  @DisplayName("Positive test. Get Manager list with some fields")
  @Test
  void getAllInfoTest() throws Exception {
    //given
    ManagerDto managerDto = getManagerDto(UUID.randomUUID());
    List<ManagerDto> managerDtoList = Collections.singletonList(managerDto);

    //when
    Mockito.when(managerService.getInfoAll()).thenReturn(managerDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(asJsonString(managerDtoList)));
    Mockito.verify(managerService, Mockito.times(1)).getInfoAll();
  }

  @DisplayName("Positive test. Get Manager with all fields")
  @Test
  void getAllTest() throws Exception {
    //given
    Manager manager = getManager(UUID.randomUUID());
    List<Manager> managerList = Collections.singletonList(manager);

    //when
    Mockito.when(managerService.getAll()).thenReturn(managerList);
    MvcResult managerGetResult = mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all/admin"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    //then
    String managerGetStringJson = managerGetResult.getResponse().getContentAsString();

    List<Manager> receivedManagerList = objectMapper.readValue(managerGetStringJson, new TypeReference<>() {
    });

    Assertions.assertEquals(managerList.size(), receivedManagerList.size());
    Assertions.assertEquals(managerList.get(0), receivedManagerList.get(0));
    Mockito.verify(managerService, Mockito.times(1)).getAll();
  }

  @DisplayName("Positive test. Create Manager")
  @Test
  void createManagerTest() throws Exception {
    //given
    ManagerDto managerDto = getManagerDto(UUID.randomUUID());
    Mockito.when(managerService.create(any(ManagerDto.class))).thenReturn(managerDto);

    //when
    MockHttpServletRequestBuilder request = post(URL_BASE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(managerDto));

    //then
    mockMvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.lastName").value("Petrov"));
    Mockito.verify(managerService, Mockito.times(1)).create(any(ManagerDto.class));
  }

  @DisplayName("Positive test. Update Manager")
  @Test
  void updateManagerTest() throws Exception {
    //given
    UUID managerId = UUID.randomUUID();
    ManagerDto managerDto = getManagerDto(managerId);

    //when
    Mockito.when(managerService.update(any(UUID.class), any(ManagerDto.class)))
            .thenReturn(managerDto);

    //then
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(URL_BASE + "/{id}", managerId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(managerDto));

    mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(managerId.toString()))
            .andExpect(jsonPath("$.lastName").value("Petrov"))
    ;
  }

  @DisplayName("Negative test. Update Manager. Not Found")
  @Test
  void updateManagerNotFoundTest() throws Exception {
    //given
    UUID managerId = UUID.randomUUID();
    ManagerDto managerDto = getManagerDto(managerId);

    //when
    Mockito.when(managerService.update(any(UUID.class), any(ManagerDto.class)))
            .thenReturn(null);

    //then
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(URL_BASE + "/{id}", managerId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(managerDto));

    mockMvc.perform(request)
            .andExpect(status().isNotFound());
    ;
  }

  /*

  @Operation(summary = "Update manager", description = "This method must be used to update existing manager in the system")
  @PutMapping("/{id}")
  public ResponseEntity<ManagerDto> updateManager(@PathVariable UUID id, @RequestBody ManagerDto managerDto) {
    ManagerDto updatedManager = managerService.update(id, managerDto);
    if (updatedManager == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedManager);
  }
  * */
}