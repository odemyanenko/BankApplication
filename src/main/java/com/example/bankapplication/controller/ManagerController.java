package com.example.bankapplication.controller;

import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth/managers")
@RequiredArgsConstructor
public class ManagerController {
  private final ManagerService managerService;

  @GetMapping("/{id}")
  public ResponseEntity<Manager> getManagerId(@PathVariable("id") UUID id){
    Optional<Manager> managerOptional = managerService.findById(id);

    if (managerOptional.isPresent()) {
      return ResponseEntity.ok(managerOptional.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Manager> createManager(@RequestBody Manager manager) {
    Manager createdManager = managerService.createManager(manager);

    return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Manager> updateManager(@PathVariable UUID id, @RequestBody Manager manager) {
    Manager updatedManager = managerService.updateManager(id, manager);
    if (updatedManager == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedManager);
  }

}
