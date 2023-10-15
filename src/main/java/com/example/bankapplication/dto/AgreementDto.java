package com.example.bankapplication.dto;

import lombok.Value;

@Value
public class AgreementDto {
  String id;
  String interestRate;
  String status;
  String sum;
  String createdAt;
}
