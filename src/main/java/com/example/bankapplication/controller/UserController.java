package com.example.bankapplication.controller;

import com.example.bankapplication.dto.UserDto;
import com.example.bankapplication.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "User Management", description = "Endpoints for user management")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @DeleteMapping(path = "/{id}")
  @PreAuthorize("hasAuthority('MANAGER')")
  public void deleteUserById(@PathVariable UUID id){
    userService.deleteById(id);
  }

  @GetMapping(path = "/{id}")
  public UserDto getUserById(@PathVariable UUID id){
    return userService.getUserById(id);
  }
}
