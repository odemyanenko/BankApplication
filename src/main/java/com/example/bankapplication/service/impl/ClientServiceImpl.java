package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Client;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.mapper.ClientMapper;
import com.example.bankapplication.mapper.ManagerMapper;
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
  public Optional<ClientDto> findById(UUID id) {
    Optional<Client> clientOptional = clientRepository.findById(id);
    if (clientOptional.isPresent()) {
      ClientDto clientDto = clientMapper.toDto(clientOptional.get());
      return Optional.of(clientDto);
    } else {
      return Optional.empty();
    }
  }
}
