package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.service.ProductService;
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
import static util.DtoCreator.getProductDto;

@DisplayName("Product controller test class")
@WebMvcTest(ProductController.class)
class ProductControllerTest {
  private final String URL_BASE = "/auth/products";

  @MockBean
  private ProductService productService;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;

  @DisplayName("Positive test. Get Product by Id")
  @Test
  void getProductByIdTest() throws Exception {
    //given
    ProductDto productDto = getProductDto(UUID.randomUUID());
    UUID productId = UUID.fromString(productDto.getId());

    //when
    Mockito.when(productService.findById(productId)).thenReturn(productDto);
    MvcResult productGetResult = mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", productId))
            .andReturn();

    //then
    Assertions.assertEquals(HttpStatus.OK.value(), productGetResult.getResponse().getStatus());

    String productGetStringJson = productGetResult.getResponse().getContentAsString();
    ProductDto receivedProductJson = objectMapper.readValue(productGetStringJson, ProductDto.class);

    Assertions.assertEquals(productDto, receivedProductJson);
  }

  @DisplayName("Negative test. Get Product by Id. Not Found")
  @Test
  void getProductByNotExistIdWithExceptionTest() throws Exception {
    //given
    UUID noExistsProductId = UUID.randomUUID();

    //when
    Mockito.when(productService.findById(noExistsProductId))
            .thenThrow(new ResourceNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}", noExistsProductId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.PRODUCT_NOT_FOUND));
  }

}