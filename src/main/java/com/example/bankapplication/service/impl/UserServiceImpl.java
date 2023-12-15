package com.example.bankapplication.service.impl;

import com.example.bankapplication.dto.UserDto;
import com.example.bankapplication.entity.User;
import com.example.bankapplication.exception.ResourceNotFoundException;
import com.example.bankapplication.mapper.UserMapper;
import com.example.bankapplication.repository.UserRepository;
import com.example.bankapplication.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.bankapplication.exception.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }

  @Override
  @Transactional
  public User findByEmailAndPassword(String email, String password) {
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      if (passwordEncoder.matches(password, user.getPassword())) {
        return user;
      }
    }
    throw new RuntimeException("Email or password is not correct");
  }

  @Override
  public UserDto getUserById(UUID id) {
    User resultUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    return userMapper.mapToDTO(resultUser);
  }
}
