package com.example.bankapplication.service;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
  Optional<ClientDto> findById(UUID id);
}
