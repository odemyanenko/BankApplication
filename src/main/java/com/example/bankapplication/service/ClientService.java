package com.example.bankapplication.service;

import com.example.bankapplication.dto.ClientDto;

import java.util.UUID;

public interface ClientService {
  ClientDto findById(UUID id);

  ClientDto findByEmail(String email);
}
