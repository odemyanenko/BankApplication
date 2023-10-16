package com.example.bankapplication.service;

import com.example.bankapplication.dto.AgreementDto;

import java.util.List;
import java.util.UUID;

public interface AgreementService {
  AgreementDto findById(UUID id);
  List<AgreementDto> findByProductManagerId(UUID id);
}
