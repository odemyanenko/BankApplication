package com.example.bankapplication.service.impl;

import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.repository.ManagerRepository;
import com.example.bankapplication.service.ManagerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
  private final ManagerRepository managerRepository;

  @Override
  public Optional<Manager> findById(UUID id) {
    return managerRepository.findById(id);
  }

  @Override
  @Transactional
  public Manager createManager(Manager manager) {
    return managerRepository.save(manager);
  }

  @Override
  @Transactional
  public Manager updateManager(UUID id, Manager manager) {
    Manager existingManager = managerRepository.findById(id).orElse(null);

    if (existingManager == null) {
      return null;
    }

    existingManager.setLastName(manager.getLastName());
    existingManager.setFirstName(manager.getFirstName());

    return managerRepository.save(manager);
  }

}
