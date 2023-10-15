package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  ClientDto toDto(Client client);
}
