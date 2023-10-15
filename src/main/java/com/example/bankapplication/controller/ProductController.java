package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ProductDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.entity.Product;
import com.example.bankapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable("id") UUID id) {
    Optional<ProductDto> productOptional = productService.findById(id);

    if (productOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(productOptional.get());
    }
  }

  @GetMapping("/{id}/full")
  public ResponseEntity<ProductInfoDto> getProductInfoById(@PathVariable("id") UUID id) {
    Optional<ProductInfoDto> productOptional = productService.findInfoById(id);

    if (productOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(productOptional.get());
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    List<ProductDto> products = productService.getAllProducts();

    if (!products.isEmpty()) {
      return ResponseEntity.ok(products);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @GetMapping("/all-active")
  public ResponseEntity<List<ProductDto>> getAllProductsActive() {
    List<ProductDto> products = productService.getAllProductsActive();

    if (!products.isEmpty()) {
      return ResponseEntity.ok(products);
    } else {
      return ResponseEntity.noContent().build();
    }
  }
}
