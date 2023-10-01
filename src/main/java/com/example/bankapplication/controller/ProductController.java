package com.example.bankapplication.controller;

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
  public ResponseEntity<Product> getProductId(@PathVariable("id") UUID id) {
    Optional<Product> productOptional = productService.findById(id);

    if (productOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(productOptional.get());
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();

    if (!products.isEmpty()) {
      return ResponseEntity.ok(products);
    } else {
      return ResponseEntity.noContent().build();
    }
  }
}
