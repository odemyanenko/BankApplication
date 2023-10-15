package com.example.bankapplication.dto;

import lombok.Value;

@Value
public class TransactionDto {
  String id;
  String type;
  String amount;
  String description;
  String status;
}
