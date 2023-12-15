package com.example.bankapplication.controller;

import com.example.bankapplication.dto.UserDto;
import com.example.bankapplication.entity.User;
import com.example.bankapplication.security.JwtProvider;
import com.example.bankapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final JwtProvider jwtProvider;

  @PostMapping
  public ResponseEntity<String> auth(@RequestBody UserDto userDto) {
    User user = userService.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    return ResponseEntity.ok().body(jwtProvider.generateToken(user.getEmail()));
  }
}
