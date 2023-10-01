package com.example.bankapplication.service;

import com.example.bankapplication.entity.Manager;

import java.util.Optional;
import java.util.UUID;

public interface ManagerService {
  Optional<Manager> findById(UUID id);
  Manager createManager(Manager manager);
  Manager updateManager(UUID id, Manager manager);
}
