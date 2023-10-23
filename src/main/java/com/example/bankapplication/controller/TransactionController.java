package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("auth/transactions")
@Tag(name = "Transaction Management", description = "Endpoints for transaction management")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @Operation(summary = "Get transaction by ID", description = "Get transaction details by their unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = TransactionDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<TransactionDto> getTransactionById(
          @Parameter(
                  description = "ID of the transaction",
                  required = true,
                  example = "97a2f126-6963-4f09-b89d-78fee3f8ba71"
          )
          @PathVariable("id") UUID id) {
    TransactionDto transactionDto = transactionService.findById(id);
    return ResponseEntity.ok(transactionDto);
  }

  @Operation(summary = "Delete transaction by ID", description = "Delete transaction by their unique ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransactionById(
          @Parameter(
                  description = "ID of the transaction",
                  required = true,
                  example = "97a2f126-6963-4f09-b89d-78fee3f8ba71"
          )
          @PathVariable("id") UUID id) {
    boolean transactionDeleted = transactionService.deleteById(id);

    if (transactionDeleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
