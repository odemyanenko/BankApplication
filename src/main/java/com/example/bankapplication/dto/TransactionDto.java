package com.example.bankapplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class TransactionDto {
  @Schema(description = "The unique identifier of the transaction")
  String id;
  @Schema(description = "The type of the transaction", allowableValues = {"PAYMENT", "REFUND", "PREAUTH", "CAPTURE", "ACCOUNT_VERIFICATION", "TRANSFER", "CASH", "DEPOSIT"})
  String type;
  @Schema(description = "The amount of the transaction")
  String amount;
  @Schema(description = "The description of the transaction")
  String description;
  @Schema(description = "The status of the transaction", allowableValues = {"NEW", "PENDING", "APPROVED"})
  String status;

  @Schema(description = "The Name of the Debit Account in transaction")
  String debitAccountName;
  @Schema(description = "The Name of the Credit Account in transaction")
  String creditAccountName;
}
