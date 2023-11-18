package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Product;
import com.example.bankapplication.entity.enums.AccountStatus;
import com.example.bankapplication.entity.enums.ProductStatus;
import com.example.bankapplication.exception.ResourceListEmptyException;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.ProductMapper;
import com.example.bankapplication.repository.ProductRepository;
import com.example.bankapplication.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DtoCreator;
import util.EntityCreator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Product service test class")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @InjectMocks
  public ProductServiceImpl productService;
  @Mock
  public ProductRepository productRepository;
  @Mock
  private ProductMapper productMapper;

  private Product product;
  private ProductDto productDto;
  private ProductInfoDto productInfoDto;

  @BeforeEach
  void setUp() {
    UUID id = UUID.randomUUID();
    UUID manager_id = UUID.randomUUID();

    product = EntityCreator.getProduct(id, manager_id);
    productDto = DtoCreator.getProductDto(id);
    productInfoDto = DtoCreator.getProductInfoDto(id, manager_id);
  }


  @DisplayName("Positive test. Get Product by Id")
  @Test
  void findByIdTest() {
    //given
    when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));
    when(productMapper.toDto(product)).thenReturn(productDto);

    //when
    ProductDto findProductDto = productService.findById(product.getId());

    //then
    Assertions.assertEquals(product.getId().toString(), findProductDto.getId());
    Assertions.assertEquals(product.getName(), findProductDto.getName());
    Assertions.assertEquals(product.getCurrencyCode().toString(), findProductDto.getCurrencyCode());
    Assertions.assertEquals(product.getInterestRate().toString(), findProductDto.getInterestRate());
    Assertions.assertEquals(product.getLimitAmount().toString(), findProductDto.getLimitAmount());
    Assertions.assertEquals(product.getStatus().toString(), findProductDto.getStatus());
  }

  @DisplayName("Positive test. Get Product with details by Id")
  @Test
  void findInfoByIdTest() {
    //given
    when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));
    when(productMapper.toInfoDto(product)).thenReturn(productInfoDto);

    //when
    ProductInfoDto findProductInfoDto = productService.findInfoById(product.getId());

    //then
    Assertions.assertEquals(product.getId().toString(), findProductInfoDto.getId());
    Assertions.assertEquals(product.getName(), findProductInfoDto.getName());
    Assertions.assertEquals(product.getCurrencyCode().toString(), findProductInfoDto.getCurrencyCode());
    Assertions.assertEquals(product.getInterestRate().toString(), findProductInfoDto.getInterestRate());
    Assertions.assertEquals(product.getLimitAmount().toString(), findProductInfoDto.getLimitAmount());
    Assertions.assertEquals(product.getStatus().toString(), findProductInfoDto.getStatus());

    Assertions.assertEquals(product.getManager().getId().toString(), findProductInfoDto.getManagerId());
    Assertions.assertEquals(product.getManager().getFirstName(), findProductInfoDto.getManagerFirstName());
    Assertions.assertEquals(product.getManager().getLastName(), findProductInfoDto.getManagerLastName());
  }

  @DisplayName("Positive test. Get Product list by Status")
  @Test
  void getAllProductsTest() {
    //given
    List<Product> products = List.of(product);
    List<ProductDto> productDtoList = List.of(productDto);
    when(productRepository.findAll()).thenReturn(products);
    when(productMapper.toDtoList(products)).thenReturn(productDtoList);

    //when
    List<ProductDto> findProducts = productService.getAllProducts();

    //then
    Assertions.assertEquals(1, findProducts.size());
  }

  @DisplayName("Positive test. Get Product list by status = ACTIVE")
  @Test
  void getAllProductsActiveTest() {
    //given
    List<Product> products = List.of(product);
    List<ProductDto> productDtoList = List.of(productDto);
    when(productRepository.getAllByStatus(ProductStatus.ACTIVE)).thenReturn(products);
    when(productMapper.toDtoList(products)).thenReturn(productDtoList);

    //when
    List<ProductDto> findProducts = productService.getAllProductsActive();

    //then
    Assertions.assertEquals(1, findProducts.size());
  }

  @DisplayName("Negative test. Get Product by Id. Get Resource Not Found Exception")
  @Test
  void findByIdWithResourceNotFoundExceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.findById(null));
  }

  @DisplayName("Negative test. Get Product Info by Id. Get Resource Not Found Exception")
  @Test
  void findInfoByIdWithResourceNotFoundExceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.findInfoById(null));
  }

  @DisplayName("Negative test. Get All Product list. Get Resource List Empty")
  @Test
  void getAllWithResourceListEmptyTest() {
    Assertions.assertEquals(0, productService.getAllProducts().size());
  }

  @DisplayName("Negative test. Get Product list by status = ACTIVE. Get Resource List Empty")
  @Test
  void getAllByStatusActiveWithResourceListEmptyTest() {
    Assertions.assertEquals(0, productService.getAllProductsActive().size());
  }

}