package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client Management", description = "Endpoints for client management")
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientService;

  @Operation(summary = "Get client by ID", description = "Get client details by their unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = ClientDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<ClientDto> getClientById(@PathVariable("id") UUID id) {
    ClientDto clientDto = clientService.findById(id);
    return ResponseEntity.ok(clientDto);
  }

}
