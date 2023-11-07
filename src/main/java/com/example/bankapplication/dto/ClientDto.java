package com.example.bankapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class ClientDto {
  String id;
  String firstName;
  String lastName;
  String taxCode;
  String email;
  String address;
  String phone;
  String createdAt;
}
