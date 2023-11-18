package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.EntityCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static util.TimeUtil.getUTCFormatTimestamp;

@DisplayName("Product mapper test class")
class ProductMapperTest {
  private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

  @DisplayName("Positive test. Product mapper to DTO test")
  @Test
  void shouldConvertEntityToDtoTest() {
    //given
    Product product = EntityCreator.getProduct(UUID.randomUUID(), UUID.randomUUID());

    //when
    ProductDto actual = productMapper.toDto(product);

    //then
    assertEquals(product.getId().toString(), actual.getId());
    assertEquals(product.getName(), actual.getName());
    assertEquals(product.getCurrencyCode().toString(), actual.getCurrencyCode());
    assertEquals(product.getInterestRate().toString(), actual.getInterestRate());
    assertEquals(product.getLimitAmount().toString(), actual.getLimitAmount());
    assertEquals(product.getStatus().toString(), actual.getStatus());
  }

  @DisplayName("Positive test. Product mapper to Info DTO test")
  @Test
  void shouldConvertEntityToInfoDtoTest() {
    //given
    Product product = EntityCreator.getProduct(UUID.randomUUID(), UUID.randomUUID());

    //when
    ProductInfoDto actual = productMapper.toInfoDto(product);

    //then
    assertEquals(product.getId().toString(), actual.getId());
    assertEquals(product.getName(), actual.getName());
    assertEquals(product.getCurrencyCode().toString(), actual.getCurrencyCode());
    assertEquals(product.getInterestRate().toString(), actual.getInterestRate());
    assertEquals(product.getLimitAmount().toString(), actual.getLimitAmount());
    assertEquals(product.getStatus().toString(), actual.getStatus());
    assertEquals(getUTCFormatTimestamp(product.getCreatedAt()), actual.getCreatedAt());
    assertEquals(getUTCFormatTimestamp(product.getUpdatedAt()), actual.getCreatedAt());

    assertEquals(product.getManager().getId().toString(), actual.getManagerId());
    assertEquals(product.getManager().getFirstName(), actual.getManagerFirstName());
    assertEquals(product.getManager().getLastName(), actual.getManagerLastName());
  }

  @DisplayName("Positive test. Product mapper to DTO list test")
  @Test
  void shouldConvertEntityToDtoListTest() {
    // given
    Product product = EntityCreator.getProduct(UUID.randomUUID(), UUID.randomUUID());
    List<Product> products = List.of(product);

    //when
    List<ProductDto> productDtoList = productMapper.toDtoList(products);

    //then
    assertEquals(1, productDtoList.size());
    assertEquals(product.getId().toString(), productDtoList.get(0).getId());
  }

  @DisplayName("Positive test. Product (with null manager) mapper to InfoDTO test")
  @Test
  void shouldWithNullManagerConvertEntityToInfoDtoTest() {
    // given
    Product product = EntityCreator.getProduct(UUID.randomUUID(), UUID.randomUUID());
    product.setId(null);
    product.getManager().setId(null);
    product.getManager().setFirstName(null);
    product.getManager().setLastName(null);

    //when
    ProductInfoDto actual = productMapper.toInfoDto(product);

    //then
    assertNull(actual.getManagerId());
    assertNull(actual.getManagerFirstName());
    assertNull(actual.getManagerLastName());
  }

  @DisplayName("Positive test. Product (with null field) mapper to InfoDTO test")
  @Test
  void shouldWithNullValueConvertEntityToInfoDtoTest() {
    // given
    Product product = EntityCreator.getProduct(UUID.randomUUID(), null);

    //when
    ProductInfoDto actual = productMapper.toInfoDto(product);

    //then
    assertNull(actual.getManagerId());
    assertNull(actual.getManagerFirstName());
    assertNull(actual.getManagerLastName());
  }

  @DisplayName("Negative test. Null mapper to DTO test")
  @Test
  void shouldReturnNullWhileConvertNullEntityToDtoTest() {
    //then
    assertNull(productMapper.toDto(null));
  }

  @DisplayName("Negative test. Null mapper to InfoDTO test")
  @Test
  void shouldReturnNullWhileConvertNullEntityToInfoDtoTest() {
    //then
    assertNull(productMapper.toInfoDto(null));
  }

  @DisplayName("Negative test. Null mapper to DTO list test")
  @Test
  void shouldReturnNullWhileConvertEntityToDtoListTest() {
    //then
    assertNull(productMapper.toDtoList(null));
  }

}