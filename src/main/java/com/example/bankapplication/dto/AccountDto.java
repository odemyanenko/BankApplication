package com.example.bankapplication.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class AccountDto{
  String id;
  String name;
  String type;
  String status;
  String balance;
}
