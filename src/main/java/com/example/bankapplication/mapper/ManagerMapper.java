package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.entity.enums.ManagerStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, ManagerStatus.class, Timestamp.class})
public interface ManagerMapper {

  ManagerDto toDto(Manager manager);

  @Mapping(target = "id", expression = "java(UUID.randomUUID())")
  @Mapping(target = "status", constant = "ACTIVE")
  @Mapping(target = "createdAt", expression = "java(getCurrentTime())")
  @Mapping(target = "updatedAt", expression = "java(getCurrentTime())")
  Manager toEntity(ManagerDto managerDto);

  List<ManagerDto> toDtoList(List<Manager> managers);

  default Timestamp getCurrentTime() {
    return new Timestamp(System.currentTimeMillis());
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "clients", ignore = true)
  @Mapping(target = "products", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "updatedAt", expression = "java(getCurrentTime())")
  Manager update(@MappingTarget Manager target, ManagerDto input);
}