package com.example.bankapplication.service;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.entity.Agreement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgreementService {
  Optional<AgreementDto> findById(UUID id);
  List<AgreementDto> findByProductManagerId(UUID id);
}
