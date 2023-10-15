package com.example.bankapplication.service;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
  Optional<ProductDto> findById(UUID id);
  Optional<ProductInfoDto> findInfoById(UUID id);
  List<ProductDto> getAllProducts();
  List<ProductDto> getAllProductsActive();
}
