package com.example.bankapplication.service.impl;

import com.example.bankapplication.entity.Product;
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

  @Override
  public Optional<Product> findById(UUID id) {
    return productRepository.findById(id);
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}
