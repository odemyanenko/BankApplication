package com.example.bankapplication.controller;

import com.example.bankapplication.service.ManagerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Manager controller test class")
@WebMvcTest(ManagerController.class)
class ManagerControllerTest {

  @MockBean
  private ManagerService managerService;

  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Manager by Id")
  @Test
  void getManagerInfoById() {
  }

  @DisplayName("Negative test. Get Manager by Id. Not Found")
  @Test
  void getManagerByNotExistIdWithExceptionTest(){
  }
}