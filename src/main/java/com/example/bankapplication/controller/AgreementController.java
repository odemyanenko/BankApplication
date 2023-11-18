package com.example.bankapplication.controller;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.dto.ProductInfoDto;
import com.example.bankapplication.service.AgreementService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("auth/agreements")
@Tag(name = "Agreements Management", description = "Endpoints for agreements management")
@RequiredArgsConstructor
public class AgreementController {
  private final AgreementService agreementService;

  @Operation(summary = "Get agreement by ID", description = "Get agreement details by their unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = AgreementDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<AgreementDto> getAgreementById(
          @Parameter(
                  description = "ID of the account",
                  required = true,
                  example = "623f89c1-47d1-4096-9a9c-5bbc674ab912"
          )
          @PathVariable("id") UUID id) {
    AgreementDto agreementDto = agreementService.findById(id);
    return ResponseEntity.ok(agreementDto);
  }

  @Operation(summary = "Get agreements list by Manager ID", description = "Get agreements list by their manager unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = AgreementDto.class))),
          @ApiResponse(responseCode = "204", description = "Empty List", content = @Content(schema = @Schema(implementation = AgreementDto.class)))
  })
  @GetMapping("all-manager/{id}")
  public ResponseEntity<List<AgreementDto>> getAgreementByProductManagerId(
          @Parameter(
                  description = "ID of the manager",
                  required = true,
                  example = "1ded32ee-855c-483c-9df3-e7d02d498d8a"
          )
          @PathVariable("id") UUID id) {
    List<AgreementDto> agreementDtoList = agreementService.findByProductManagerId(id);

    if (!agreementDtoList.isEmpty()) {
      return ResponseEntity.ok(agreementDtoList);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

}
