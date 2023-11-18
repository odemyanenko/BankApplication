package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Agreement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.EntityCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static util.TimeUtil.getUTCFormatTimestamp;

@DisplayName("Agreement mapper test class")
class AgreementMapperTest {
  private final AgreementMapper agreementMapper = Mappers.getMapper(AgreementMapper.class);

  @DisplayName("Positive test. Agreement mapper to DTO test")
  @Test
  void shouldConvertEntityToDtoTest() {
    // given
    Agreement agreement = EntityCreator.getAgreement(UUID.randomUUID());

    //when
    AgreementDto actual = agreementMapper.toDto(agreement);

    //then
    assertEquals(agreement.getId().toString(), actual.getId());
    assertEquals(agreement.getInterestRate().toString(), actual.getInterestRate());
    assertEquals(agreement.getStatus().toString(), actual.getStatus());
    assertEquals(agreement.getSum().toString(), actual.getSum());
    assertEquals(getUTCFormatTimestamp(agreement.getCreatedAt()), actual.getCreatedAt());
  }

  @DisplayName("Positive test. Agreement mapper to DTO list test")
  @Test
  void shouldConvertEntityToDtoListTest() {
    // given
    Agreement agreement = EntityCreator.getAgreement(UUID.randomUUID());
    List<Agreement> agreements = List.of(agreement);

    //when
    List<AgreementDto> agreementDtoList = agreementMapper.toDtoList(agreements);

    //then
    assertEquals(1, agreementDtoList.size());
    assertEquals(agreement.getId().toString(), agreementDtoList.get(0).getId());
  }

  @DisplayName("Positive test. Agreement (with null field) mapper to DTO test")
  @Test
  void shouldWithNullValueConvertEntityToDtoTest() {
    // given
    Agreement agreement = EntityCreator.getAgreement(UUID.randomUUID());
    agreement.setId(null);
    agreement.setInterestRate(null);
    agreement.setSum(null);
    agreement.setStatus(null);

    //when
    AgreementDto actual = agreementMapper.toDto(agreement);

    //then
    assertNull(actual.getId());
    assertNull(actual.getInterestRate());
    assertNull(actual.getSum());
    assertNull(actual.getStatus());
  }

  @DisplayName("Negative test. Null mapper to DTO test")
  @Test
  void shouldReturnNullWhileConvertNullEntityToDtoTest() {
    assertNull(agreementMapper.toDto(null));
  }

  @DisplayName("Negative test. Null mapper to DTO list test")
  @Test
  void shouldReturnNullWhileConvertEntityToDtoListTest() {
    assertNull(agreementMapper.toDtoList(null));
  }

}