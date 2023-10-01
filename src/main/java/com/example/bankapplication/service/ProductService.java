package com.example.bankapplication.service;

import com.example.bankapplication.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
  Optional<Product> findById(UUID id);
  List<Product> getAllProducts();
}
