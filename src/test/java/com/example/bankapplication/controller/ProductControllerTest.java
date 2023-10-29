package com.example.bankapplication.controller;

import com.example.bankapplication.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Product controller test class")
@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @MockBean
  private ProductService productService;

  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Product by Id")
  @Test
  void getProductById() {
  }

  @DisplayName("Negative test. Get Product by Id. Not Found")
  @Test
  void getProductByNotExistIdWithExceptionTest(){
  }
}