package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.entity.Agreement;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.AgreementMapper;
import com.example.bankapplication.repository.AgreementRepository;
import com.example.bankapplication.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
  private final AgreementRepository agreementRepository;
  private final AgreementMapper agreementMapper;

  @Override
  public AgreementDto findById(UUID id) {
    Optional<Agreement> agreementOptional = agreementRepository.findById(id);
    Agreement agreement = agreementOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.AGREEMENT_NOT_FOUND));

    return agreementMapper.toDto(agreement);
  }

  @Override
  public List<AgreementDto> findByProductManagerId(UUID id) {
    List<Agreement> agreements = agreementRepository.findAllByProductManagerId(id);
    return agreementMapper.toDtoList(agreements);
  }

}
