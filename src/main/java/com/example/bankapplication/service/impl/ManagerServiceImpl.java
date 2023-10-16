package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.exception.ErrorMessage;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.ManagerMapper;
import com.example.bankapplication.repository.ManagerRepository;
import com.example.bankapplication.service.ManagerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
  private final ManagerRepository managerRepository;
  private final ManagerMapper managerMapper;

  @Override
  public ManagerDto findById(UUID id) {
    Optional<Manager> managerOptional = managerRepository.findById(id);
    Manager manager = managerOptional.orElseThrow(() ->
            new ResourceNotFoundException(ErrorMessage.MANAGER_NOT_FOUND));

    return managerMapper.toDto(manager);
  }

  @Override
  public List<Manager> getAll() {
    return managerRepository.findAll();
  }

  @Override
  public List<ManagerDto> getInfoAll() {
    List<Manager> managers = managerRepository.findAll();
    return managerMapper.toDtoList(managers);
  }

  @Override
  @Transactional
  public ManagerDto create(ManagerDto managerDto) {
    Manager manager = managerMapper.toEntity(managerDto);
    Manager createdManager = managerRepository.save(manager);

    return managerMapper.toDto(createdManager);
  }

  @Override
  @Transactional
  public ManagerDto update(UUID id, ManagerDto managerDto) {
    Manager existingManager = managerRepository.findById(id).orElse(null);

    if (existingManager == null) {
      return null;
    }

    Manager updatedManagerDto  = managerMapper.toEntity(managerDto);
    Manager updatedManager = managerRepository.save(updatedManagerDto);

    return managerMapper.toDto(updatedManager);
  }

}
