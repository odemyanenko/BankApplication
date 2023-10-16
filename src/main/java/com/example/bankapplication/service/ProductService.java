package com.example.bankapplication.service;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
  ProductDto findById(UUID id);
  ProductInfoDto findInfoById(UUID id);
  List<ProductDto> getAllProducts();
  List<ProductDto> getAllProductsActive();
}
