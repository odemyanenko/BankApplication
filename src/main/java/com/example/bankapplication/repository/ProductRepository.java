package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Product;
import com.example.bankapplication.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
  Optional<Product> findById(UUID id);
  List<Product> getAllByStatus(ProductStatus status);
}
