package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/clients")
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientService;

  @GetMapping("/{id}")
  public ResponseEntity<ClientDto> getClientById(@PathVariable("id") UUID id) {
    Optional<ClientDto> clientDtoOptional = clientService.findById(id);
    if (clientDtoOptional.isPresent()) {
      return ResponseEntity.ok(clientDtoOptional.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
