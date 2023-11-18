package com.example.bankapplication.service;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.ManagerMapper;
import com.example.bankapplication.repository.ManagerRepository;
import com.example.bankapplication.service.impl.ManagerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DtoCreator;
import util.EntityCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Manager service test class")
@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {
  @InjectMocks
  public ManagerServiceImpl managerService;
  @Mock
  public ManagerRepository managerRepository;
  @Mock
  private ManagerMapper managerMapper;

  private Manager manager;
  private ManagerDto managerDto;

  @BeforeEach
  void setUp() {
    UUID id = UUID.randomUUID();
    manager = EntityCreator.getManager(id);
    managerDto = DtoCreator.getManagerDto(id);
  }

  @DisplayName("Positive test. Get Manager by Id")
  @Test
  void findByIdTest() {
    //given
    when(managerRepository.findById(any(UUID.class))).thenReturn(Optional.of(manager));
    when(managerMapper.toDto(manager)).thenReturn(managerDto);

    //when
    ManagerDto findManagerDto = managerService.findById(manager.getId());

    //then
    Assertions.assertEquals(manager.getId().toString(), findManagerDto.getId());
    Assertions.assertEquals(manager.getLastName(), findManagerDto.getLastName());
    Assertions.assertEquals(manager.getFirstName(), findManagerDto.getFirstName());
  }

  @DisplayName("Negative test. Get Manager by Id. Get Resource Not Found Exception")
  @Test
  void findByIdWithResourceNotFoundExceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> managerService.findById(null));
  }

  @DisplayName("Positive test. Get Manager list")
  @Test
  void getAllTest() {
    //given
    List<Manager> managers = List.of(manager);
    when(managerRepository.findAll()).thenReturn(managers);

    //when
    List<Manager> findManagers = managerService.getAll();

    //then
    Assertions.assertEquals(1, findManagers.size());
  }

  @DisplayName("Negative test. Get Manager list. Get Resource List Empty")
  @Test
  void getAllWithResourceListEmptyTest() {
    //given
    List<Manager> managers = new ArrayList<>();
    when(managerRepository.findAll()).thenReturn(managers);

    //when
    List<Manager> findManagers = managerService.getAll();

    //then
    Assertions.assertEquals(0, findManagers.size());
  }

  @DisplayName("Positive test. Get Manager list with details")
  @Test
  void getInfoAll() {
    //given
    List<Manager> managers = List.of(manager);
    List<ManagerDto> managerDtoList = List.of(managerDto);
    when(managerRepository.findAll()).thenReturn(managers);
    when(managerMapper.toDtoList(managers)).thenReturn(managerDtoList);

    //when
    List<ManagerDto> findManagerDtos = managerService.getInfoAll();

    //then
    Assertions.assertEquals(1, findManagerDtos.size());
  }

  @DisplayName("Negative test. Get Manager list with details. Get Resource List Empty")
  @Test
  void getInfoAllWithResourceListEmptyTest() {
    //given
    List<Manager> managers = new ArrayList<>();
    List<ManagerDto> managerDtoList = new ArrayList<>();
    when(managerRepository.findAll()).thenReturn(managers);
    when(managerMapper.toDtoList(managers)).thenReturn(managerDtoList);

    //when
    List<ManagerDto> findManagerDtos = managerService.getInfoAll();

    //then
    Assertions.assertEquals(0, findManagerDtos.size());
  }

  @DisplayName("Positive test. Create Manager")
  @Test
  void createTest() {
    //given
    when(managerMapper.toEntity(managerDto)).thenReturn(manager);
    when(managerRepository.save(manager)).thenReturn(manager);
    when(managerMapper.toDto(manager)).thenReturn(managerDto);

    //when
    ManagerDto createdManagerDto = managerService.create(managerDto);

    //then
    Assertions.assertNotNull(createdManagerDto);
    Assertions.assertEquals(managerDto.getId(), createdManagerDto.getId());
    Assertions.assertEquals(managerDto.getLastName(), createdManagerDto.getLastName());
    Assertions.assertEquals(managerDto.getFirstName(), createdManagerDto.getFirstName());
  }

  @DisplayName("Positive test. Update Manager")
  @Test
  void updateTest() {
    //given
    UUID managerId = manager.getId();
    when(managerRepository.findById(any(UUID.class))).thenReturn(Optional.of(manager));
    when(managerRepository.save(any(Manager.class))).thenReturn(manager);
    when(managerMapper.toEntity(any(ManagerDto.class))).thenReturn(manager);
    when(managerMapper.toDto(any(Manager.class))).thenReturn(managerDto);

    //when
    ManagerDto updatedManagerDto = managerService.update(managerId, managerDto);

    //then
    Assertions.assertNotNull(updatedManagerDto);
    Assertions.assertEquals(managerId.toString(), updatedManagerDto.getId());
  }

  @DisplayName("Negative test. Update Manager. Manager not found")
  @Test
  void updateWhenManagerNotFoundTest() {
    //given
    UUID managerId = UUID.randomUUID();
    when(managerRepository.findById(managerId)).thenReturn(Optional.empty());

    //when
    ManagerDto updatedManagerDto = managerService.update(managerId, managerDto);

    //then
    Assertions.assertNull(updatedManagerDto);
    Mockito.verify(managerRepository, Mockito.times(0)).save(manager);
  }

}