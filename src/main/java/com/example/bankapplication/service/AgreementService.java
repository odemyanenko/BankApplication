package com.example.bankapplication.service;

import com.example.bankapplication.entity.Agreement;

import java.util.Optional;
import java.util.UUID;

public interface AgreementService {
  Optional<Agreement> findById(UUID id);
}
