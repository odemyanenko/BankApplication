package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.entity.Product;
import com.example.bankapplication.mapper.ManagerMapper;
import com.example.bankapplication.mapper.ProductMapper;
import com.example.bankapplication.repository.ManagerRepository;
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
  public Optional<ProductDto> findById(UUID id) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (productOptional.isPresent()) {
      ProductDto productDto = productMapper.toDto(productOptional.get());
      return Optional.of(productDto);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<ProductInfoDto> findInfoById(UUID id) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (productOptional.isPresent()) {
      ProductInfoDto productDto = productMapper.toInfoDto(productOptional.get());
      return Optional.of(productDto);
    } else {
      return Optional.empty();
    }
  }
  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}
