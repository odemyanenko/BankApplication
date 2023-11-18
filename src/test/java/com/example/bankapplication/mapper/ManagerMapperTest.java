package com.example.bankapplication.mapper;

import com.example.bankapplication.dto.AccountDto;
import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.entity.enums.ManagerStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.DtoCreator;
import util.EntityCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Manager mapper test class")
class ManagerMapperTest {
  private final ManagerMapper managerMapper = Mappers.getMapper(ManagerMapper.class);

  @DisplayName("Positive test. Manager mapper to DTO test")
  @Test
  void shouldConvertEntityToDtoTest() {
    // given
    Manager manager = EntityCreator.getManager(UUID.randomUUID());

    //when
    ManagerDto actual = managerMapper.toDto(manager);

    //then
    assertEquals(manager.getId().toString(), actual.getId());
    assertEquals(manager.getLastName(), actual.getLastName());
    assertEquals(manager.getFirstName(), actual.getFirstName());
  }

  @DisplayName("Positive test. ManagerDto mapper to Manager test")
  @Test
  void shouldConvertDtotoEntityTest() {
    // given
    ManagerDto managerDto = DtoCreator.getManagerDto(UUID.randomUUID());

    //when
    Manager actual = managerMapper.toEntity(managerDto);

    //then
    assertNotEquals(managerDto.getId(), actual.getId().toString());
    assertEquals(managerDto.getLastName(), actual.getLastName());
    assertEquals(managerDto.getFirstName(), actual.getFirstName());
    assertEquals(ManagerStatus.ACTIVE, actual.getStatus());
  }

  @DisplayName("Positive test. Manager mapper to DTO list test")
  @Test
  void shouldConvertEntityToDtoListTest() {
    // given
    Manager manager = EntityCreator.getManager(UUID.randomUUID());
    List<Manager> managers = List.of(manager);

    //when
    List<ManagerDto> managerDtoList = managerMapper.toDtoList(managers);

    //then
    assertEquals(1, managerDtoList.size());
    assertEquals(manager.getId().toString(), managerDtoList.get(0).getId());
  }

  @DisplayName("Positive test. Update Manager mapper from DTO test")
  @Test
  void shouldUpdateEntityFromDtoTest() {
    // given
    UUID managerId = UUID.randomUUID();
    ManagerDto managerDto = DtoCreator.getManagerDto(managerId);
    Manager sourceManager = EntityCreator.getManager(managerId);
    sourceManager.setLastName("LastName");
    sourceManager.setFirstName("FirstName");

    //when
    Manager actual = managerMapper.update(sourceManager, managerDto);

    //then
    assertEquals(managerId, actual.getId());
    assertEquals(managerDto.getLastName(), actual.getLastName());
    assertEquals(managerDto.getFirstName(), actual.getFirstName());
  }

  @DisplayName("Negative test. Null mapper to DTO test. DTO = NULL")
  @Test
  void shouldReturnNullWhileConvertNullEntityToDtoTest() {
    assertNull(managerMapper.toDto(null));
  }

  @DisplayName("Negative test. Null mapper to Entity test. Entity = NULL")
  @Test
  void shouldReturnNullWhileConvertNullDtoToEntityTest() {
    assertNull(managerMapper.toEntity(null));
  }

  @DisplayName("Negative test. Null managers List mapper to Dto List test. Dto List = NULL")
  @Test
  void shouldReturnNullWhileConvertNullManagerListToDtoListTest() {
    assertNull(managerMapper.toDtoList(null));
  }

  @DisplayName("Negative test. Update Manager mapper from DTO = NULL test. Updated Manager = Input Manager")
  @Test
  void shouldReturnInputManagerWhileConvertDtoNullToUpdatedManagerTest() {
    // given
    UUID managerId = UUID.randomUUID();
    Manager sourceManager = EntityCreator.getManager(managerId);
    sourceManager.setLastName("LastName");
    sourceManager.setFirstName("FirstName");

    //when
    Manager actual = managerMapper.update(sourceManager, null);

    //then
    assertEquals(managerId, actual.getId());
    assertEquals(sourceManager.getLastName(), actual.getLastName());
    assertEquals(sourceManager.getFirstName(), actual.getFirstName());
  }

}