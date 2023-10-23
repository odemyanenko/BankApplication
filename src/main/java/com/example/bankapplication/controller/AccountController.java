package com.example.bankapplication.controller;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("auth/accounts")
@Tag(name = "Account Management", description = "Endpoints for account management")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @Operation(summary = "Get account by ID", description = "Get account details by their unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = AccountDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<AccountDto> getAccountById(
          @Parameter(
                  description = "ID of the account",
                  required = true
          )
          @PathVariable("id") UUID id) {
    AccountDto accountDto = accountService.findById(id);
    return ResponseEntity.ok(accountDto);
  }
}
