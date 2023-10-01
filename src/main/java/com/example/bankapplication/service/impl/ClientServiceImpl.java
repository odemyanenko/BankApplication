package com.example.bankapplication.service.impl;

import com.example.bankapplication.entity.Client;
import com.example.bankapplication.repository.ClientRepository;
import com.example.bankapplication.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
  private final ClientRepository clientRepository;

  @Override
  public Optional<Client> findById(UUID id) {
    return clientRepository.findById(id);
  }
}
