package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.EntityCreator;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Client mapper test class")
class ClientMapperTest {
  private final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

  @DisplayName("Positive test. Client mapper to DTO test")
  @Test
  void shouldConvertEntityToDtoTest() {
    // given
    Client expected = EntityCreator.getClient(UUID.randomUUID());

    //when
    ClientDto actual = clientMapper.toDto(expected);

    //then
    assertEquals(expected.getId().toString(), actual.getId());
    assertEquals(expected.getFirstName(), actual.getFirstName());
    assertEquals(expected.getLastName(), actual.getLastName());
    assertEquals(expected.getTaxCode(), actual.getTaxCode());
    assertEquals(expected.getEmail(), actual.getEmail());
    assertEquals(expected.getAddress(), actual.getAddress());
    assertEquals(expected.getPhone(), actual.getPhone());
    assertEquals(expected.getCreatedAt().toString(), actual.getCreatedAt());
  }

  @DisplayName("Negative test. Null mapper to DTO test")
  @Test
  void shouldReturnNullWhileConvertNullEntityToDtoTest() {
    assertNull(clientMapper.toDto(null));
  }

  @DisplayName("Positive test. Client (with null field) mapper to DTO test")
  @Test
  void shouldWithNullValueConvertEntityToDtoTest() {
    // given
    Client client = EntityCreator.getClient(UUID.randomUUID());
    client.setId(null);

    //when
    ClientDto actual = clientMapper.toDto(client);

    //then
    assertNull(actual.getId());
  }

}