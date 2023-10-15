package com.example.bankapplication.controller;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.service.AgreementService;
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
@RequestMapping("auth/agreements")
@RequiredArgsConstructor
public class AgreementController {
  private final AgreementService agreementService;

  @GetMapping("/{id}")
  public ResponseEntity<AgreementDto> getAgreementById(@PathVariable("id") UUID id) {
    Optional<AgreementDto> agreementOptional = agreementService.findById(id);
    if (agreementOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(agreementOptional.get());
    }
  }
  @GetMapping("all-manager/{id}")
  public ResponseEntity<List<AgreementDto>> getAgreementByProductManagerId(@PathVariable("id") UUID id){
    List<AgreementDto> agreementDtoList = agreementService.findByProductManagerId(id);
    return ResponseEntity.ok(agreementDtoList);
  }

}
