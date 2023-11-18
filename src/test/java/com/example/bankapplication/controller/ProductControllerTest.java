package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.DtoCreator.getProductDto;
import static util.DtoCreator.getProductInfoDto;

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

  @DisplayName("Positive test. Get Product with full details by Id")
  @Test
  void getProductInfoByIdTest() throws Exception {
    //given
    ProductInfoDto productInfoDto = getProductInfoDto(UUID.randomUUID(), UUID.randomUUID());
    UUID productId = UUID.fromString(productInfoDto.getId());

    //when
    Mockito.when(productService.findInfoById(productId)).thenReturn(productInfoDto);
    MvcResult productGetResult = mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}/full", productId))
            .andReturn();

    //then
    Assertions.assertEquals(HttpStatus.OK.value(), productGetResult.getResponse().getStatus());

    String productGetStringJson = productGetResult.getResponse().getContentAsString();
    ProductInfoDto receivedProductJson = objectMapper.readValue(productGetStringJson, ProductInfoDto.class);

    Assertions.assertEquals(productInfoDto, receivedProductJson);
  }

  @DisplayName("Negative test. Get Product with full details by Id. Not Found")
  @Test
  void getProductInfoByNotExistIdWithExceptionTest() throws Exception {
    //given
    UUID noExistsProductId = UUID.randomUUID();

    //when
    Mockito.when(productService.findInfoById(noExistsProductId))
            .thenThrow(new ResourceNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/{id}/full", noExistsProductId))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode").exists())
            .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(ErrorMessage.PRODUCT_NOT_FOUND));
  }

  @DisplayName("Positive test. Get Product list details")
  @Test
  void getAllProductsTest() throws Exception {
    //given
    ProductDto productDto = getProductDto(UUID.randomUUID());
    List<ProductDto> productDtoList = Collections.singletonList(productDto);

    //when
    Mockito.when(productService.getAllProducts()).thenReturn(productDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(JsonUtil.asJsonString(productDtoList)));
    Mockito.verify(productService, Mockito.times(1)).getAllProducts();
  }

  @DisplayName("Negative test. Get Product list details. Empty List")
  @Test
  void getProductEmptyListTest() throws Exception {
    //given
    List<ProductDto> productEmptyDtoList = new ArrayList<>();

    //when
    Mockito.when(productService.getAllProducts())
            .thenReturn(productEmptyDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all"))
            .andExpect(status().isNoContent());
    Mockito.verify(productService, Mockito.times(1)).getAllProducts();
  }

  @DisplayName("Positive test. Get Product list details with status = ACTIVE")
  @Test
  void getAllProductsActiveTest() throws Exception {
    //given
    ProductDto productDto = getProductDto(UUID.randomUUID());
    List<ProductDto> productDtoList = Collections.singletonList(productDto);

    //when
    Mockito.when(productService.getAllProductsActive()).thenReturn(productDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all-active"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(JsonUtil.asJsonString(productDtoList)));
    Mockito.verify(productService, Mockito.times(1)).getAllProductsActive();
  }

  @DisplayName("Negative test. Get Product list details with status = ACTIVE. Empty List")
  @Test
  void getProductEmptyListByActiveStatusTest() throws Exception {
    //given
    List<ProductDto> productEmptyDtoList = new ArrayList<>();

    //when
    Mockito.when(productService.getAllProductsActive())
            .thenReturn(productEmptyDtoList);

    //then
    mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE + "/all-active"))
            .andExpect(status().isNoContent());
    Mockito.verify(productService, Mockito.times(1)).getAllProductsActive();
  }

}