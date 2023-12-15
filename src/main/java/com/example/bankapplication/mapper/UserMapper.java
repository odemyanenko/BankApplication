package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.UserDto;
import com.example.bankapplication.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "password", ignore = true)
  UserDto mapToDTO(User resultUser);
}