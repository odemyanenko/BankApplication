package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  @Mapping(target = "createdAt", expression = "java(convertTimestampToString(client.getCreatedAt()))")
  ClientDto toDto(Client client);

  default String convertTimestampToString(Timestamp timestamp) {
    return timestamp.toString();
  }
}
