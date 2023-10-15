package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Agreement;
import com.example.bankapplication.entity.Manager;
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
  public Optional<AgreementDto> findById(UUID id) {
    Optional<Agreement> agreementOptional = agreementRepository.findById(id);
    if (agreementOptional.isPresent()) {
      AgreementDto agreementDto = agreementMapper.toDto(agreementOptional.get());
      return Optional.of(agreementDto);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public List<AgreementDto> findByProductManagerId(UUID id) {
    List<Agreement> agreements = agreementRepository.findAllByProductManagerId(id);
    return agreementMapper.toDtoList(agreements);
  }

}
