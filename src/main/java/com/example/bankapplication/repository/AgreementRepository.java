package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
  Optional<Agreement> findById(UUID id);
  @Query("select a from Agreement a where a.product.manager.id = :id")
  List<Agreement> findAllByProductManagerId(@Param("id") UUID id);
}
