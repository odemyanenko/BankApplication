package com.example.bankapplication.service;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
  ManagerDto findById(UUID id);

  List<Manager> getAll();
  List<ManagerDto> getInfoAll();

  ManagerDto create(ManagerDto managerDto);
  ManagerDto update(UUID id, ManagerDto managerDto);
}
