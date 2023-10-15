package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.entity.Agreement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
  AgreementDto toDto(Agreement agreement);
  List<AgreementDto> toDtoList(List<Agreement> agreements);
}
