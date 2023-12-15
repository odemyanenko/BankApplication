package com.example.bankapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

@Value
public class AccountDto {
  @Schema(description = "The unique identifier of the account")
  String id;

  @Schema(description = "The number of the account")
  String name;

  @Schema(description = "The type of the account", allowableValues = {"CHECKING", "SAVINGS", "LOAN", "DEBIT_CARD", "CREDIT_CARD", "OTHER"})
  String type;

  @Schema(description = "The status of the account", allowableValues = {"ACTIVE", "INACTIVE", "BLOCKED"})
  String status;

  @Schema(description = "The balance of the account")
  String balance;

  @Schema(description = "The currency code of the account")
  String currencyCode;
}
