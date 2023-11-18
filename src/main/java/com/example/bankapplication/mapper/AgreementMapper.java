package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
  @Mapping(target = "createdAt", expression = "java(getFormatUTCData(agreement.getCreatedAt()))")
  AgreementDto toDto(Agreement agreement);

  default String getFormatUTCData(Timestamp datetime) {
    DateFormat dfActual = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    dfActual.setTimeZone(TimeZone.getTimeZone("UTC"));
    return dfActual.format(datetime);
  }

  List<AgreementDto> toDtoList(List<Agreement> agreements);
}
