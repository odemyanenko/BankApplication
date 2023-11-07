package com.example.bankapplication.service;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.Client;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.ClientMapper;
import com.example.bankapplication.repository.ClientRepository;
import com.example.bankapplication.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DtoCreator;
import util.EntityCreator;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Client service test class")
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
  @InjectMocks
  public ClientServiceImpl clientService;
  @Mock
  public ClientRepository clientRepository;
  @Mock
  private ClientMapper clientMapper;

  private Client client;
  private ClientDto clientDto;

  @BeforeEach
  void setUp() {
    UUID id = UUID.randomUUID();
    client = EntityCreator.getClient(id);
    clientDto = DtoCreator.getClientDto(id);
  }

  @DisplayName("Positive test. Get Client by Id")
  @Test
  void findByIdTest() {
    //given
    when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
    when(clientMapper.toDto(client)).thenReturn(clientDto);

    //when
    ClientDto findClientDto = clientService.findById(client.getId());

    //then
    Assertions.assertEquals(client.getId().toString(), findClientDto.getId());
    Assertions.assertEquals(client.getTaxCode(), findClientDto.getTaxCode());
  }

  @DisplayName("Negative test. Get Client by Id. Get Resource Not Found Exception")
  @Test
  void findByIdWithResourceNotFoundExceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> clientService.findById(null));
  }

}