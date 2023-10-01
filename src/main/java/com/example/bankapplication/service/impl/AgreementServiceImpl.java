package com.example.bankapplication.service.impl;

import com.example.bankapplication.entity.Agreement;
import com.example.bankapplication.repository.AgreementRepository;
import com.example.bankapplication.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
  private final AgreementRepository agreementRepository;

  @Override
  public Optional<Agreement> findById(UUID id) {
    return agreementRepository.findById(id);
  }
}
