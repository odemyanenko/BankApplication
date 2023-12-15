package com.example.bankapplication.service;

import com.example.bankapplication.dto.UserDto;
import com.example.bankapplication.entity.User;
import jakarta.transaction.Transactional;

import java.util.UUID;

public interface UserService {

  void deleteById(UUID id);

  User findByEmailAndPassword(String email, String password);

  UserDto getUserById(UUID id);
}
