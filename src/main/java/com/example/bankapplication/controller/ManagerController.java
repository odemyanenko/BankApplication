package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("auth/managers")
@RequiredArgsConstructor
public class ManagerController {
  private final ManagerService managerService;

  @GetMapping("/{id}")
  public ResponseEntity<ManagerDto> getManagerInfoById(@PathVariable("id") UUID id){
    ManagerDto managerDto = managerService.findById(id);
    return ResponseEntity.ok(managerDto);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ManagerDto>> getAllInfo(){
    List<ManagerDto> managerDtoList = managerService.getInfoAll();

    return ResponseEntity.ok(managerDtoList);
  }

  @GetMapping("/all/admin")
  public ResponseEntity<List<Manager>> getAll(){
    List<Manager> managerList = managerService.getAll();

    return ResponseEntity.ok(managerList);
  }

  @PostMapping
  public ResponseEntity<ManagerDto> createManager(@RequestBody ManagerDto managerDto) {
    ManagerDto createdManagerDto = managerService.create(managerDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdManagerDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ManagerDto> updateManager(@PathVariable UUID id, @RequestBody ManagerDto managerDto) {
    ManagerDto updatedManager = managerService.update(id, managerDto);
    if (updatedManager == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedManager);
  }

}
