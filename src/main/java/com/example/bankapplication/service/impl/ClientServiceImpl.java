package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.Client;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.ClientMapper;
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
  private final ClientMapper clientMapper;

  @Override
  public ClientDto findById(UUID id) {
    Optional<Client> clientOptional = clientRepository.findById(id);
    Client client = clientOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.CLIENT_NOT_FOUND));

    return clientMapper.toDto(client);
  }

  @Override
  public ClientDto findByEmail(String email) {
    Optional<Client> clientOptional = clientRepository.findByEmail(email);
    Client client = clientOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.CLIENT_NOT_FOUND));

    return clientMapper.toDto(client);
  }
}
