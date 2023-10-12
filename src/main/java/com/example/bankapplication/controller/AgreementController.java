package com.example.bankapplication.controller;

import com.example.bankapplication.entity.Agreement;
import com.example.bankapplication.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/agreements")
@RequiredArgsConstructor
public class AgreementController {
  private final AgreementService agreementService;

  @GetMapping("/{id}")
  public Optional<Agreement> getAgreementById(@PathVariable("id") UUID id) {
    return agreementService.findById(id);
  }
}
