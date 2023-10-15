package com.example.bankapplication.dto;

import lombok.Value;

@Value
public class ClientDto {
  String id;
  String firstName;
  String lastName;
  String eMail;
  String address;
  String phone;
  String createdAt;
}
