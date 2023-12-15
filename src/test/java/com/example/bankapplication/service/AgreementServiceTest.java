package com.example.bankapplication.service;

import com.example.bankapplication.dto.AgreementDto;
import com.example.bankapplication.entity.Agreement;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.AgreementMapper;
import com.example.bankapplication.repository.AgreementRepository;
import com.example.bankapplication.service.impl.AgreementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DtoCreator;
import util.EntityCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Agreement service test class")
@ExtendWith(MockitoExtension.class)
class AgreementServiceTest {
  @InjectMocks
  public AgreementServiceImpl agreementService;
  @Mock
  public AgreementRepository agreementRepository;
  @Mock
  private AgreementMapper agreementMapper;

  private Agreement agreement;
  private AgreementDto agreementDto;

  @BeforeEach
  void setUp() {
    UUID id = UUID.randomUUID();
    agreement = EntityCreator.getAgreement(id);
    agreementDto = DtoCreator.getAgreementDto(id);
  }

  @DisplayName("Positive test. Get Agreement by Id")
  @Test
  void findByIdTest() {
    //given
    when(agreementRepository.findById(any(UUID.class))).thenReturn(Optional.of(agreement));
    when(agreementMapper.toDto(agreement)).thenReturn(agreementDto);

    //when
    AgreementDto findAgreementDto = agreementService.findById(agreement.getId());

    //then
    Assertions.assertEquals(agreement.getId().toString(), findAgreementDto.getId());
    Assertions.assertEquals(agreement.getStatus().toString(), findAgreementDto.getStatus());
  }

  @DisplayName("Positive test. Get Agreement list By Product ManagerId")
  @Test
  void findByProductManagerIdTest() {
    //given
    UUID managerId = UUID.randomUUID();
    List<Agreement> agreements = List.of(agreement);
    List<AgreementDto> agreementDtoList = List.of(agreementDto);

    when(agreementRepository.findAllByProductManagerId(managerId)).thenReturn(agreements);
    when(agreementMapper.toDtoList(agreements)).thenReturn(agreementDtoList);

    //when
    List<AgreementDto> findAgreements = agreementService.findByProductManagerId(managerId);

    //then
    Assertions.assertEquals(1, findAgreements.size());
  }

  @DisplayName("Negative test. Get Agreement by Id. Get Resource Not Found Exception")
  @Test
  void findByIdWithResourceNotFoundExceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> agreementService.findById(null));
  }

  @DisplayName("Negative test. Get Agreement list by product managerId. Get Resource List Empty")
  @Test
  void findByProductManagerIdListEmptyTest() {
    //given
    UUID managerId = UUID.randomUUID();
    List<Agreement> agreements = new ArrayList<>();
    when(agreementRepository.findAllByProductManagerId(managerId)).thenReturn(agreements);

    //when
    List<AgreementDto> findAgreements = agreementService.findByProductManagerId(managerId);

    //then
    Assertions.assertEquals(0, findAgreements.size());
  }

}