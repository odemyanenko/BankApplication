package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Product;
import com.example.bankapplication.entity.enums.ProductStatus;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.ProductMapper;
import com.example.bankapplication.repository.ProductRepository;
import com.example.bankapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public ProductDto findById(UUID id) {
    Optional<Product> productOptional = productRepository.findById(id);
    Product product = productOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

    return productMapper.toDto(product);
  }

  @Override
  public ProductInfoDto findInfoById(UUID id) {
    Optional<Product> productOptional = productRepository.findById(id);
    Product product = productOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

    return productMapper.toInfoDto(product);
  }

  @Override
  public List<ProductDto> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return productMapper.toDtoList(products);
  }

  @Override
  public List<ProductDto> getAllProductsActive() {
    List<Product> products = productRepository.getAllByStatus(ProductStatus.ACTIVE);
    return productMapper.toDtoList(products);
  }
}
