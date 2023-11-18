package com.example.bankapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

@Value
public class ProductInfoDto {
  String id;
  String name;
  String currencyCode;
  String interestRate;
  String limitAmount;
  String status;
  String createdAt;
  String updatedAt;

  String managerId;
  String managerFirstName;
  String managerLastName;
}
