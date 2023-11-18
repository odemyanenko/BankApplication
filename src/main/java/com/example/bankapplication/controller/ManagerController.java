package com.example.bankapplication.controller;

import com.example.bankapplication.dto.ClientDto;
import com.example.bankapplication.dto.ManagerDto;
import com.example.bankapplication.entity.Manager;
import com.example.bankapplication.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("auth/managers")
@Tag(name = "Manager Management", description = "Endpoint for manager management")
@RequiredArgsConstructor
public class ManagerController {
  private final ManagerService managerService;

  @Operation(summary = "Get manager by ID", description = "Get manager details by their unique ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = ManagerDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<ManagerDto> getManagerInfoById(@PathVariable("id") UUID id){
    ManagerDto managerDto = managerService.findById(id);
    return ResponseEntity.ok(managerDto);
  }

  @Operation(summary = "Get manager list", description = "Get manager list details")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = List.class))),
  })
  @GetMapping("/all")
  public ResponseEntity<List<ManagerDto>> getAllInfo(){
    List<ManagerDto> managerDtoList = managerService.getInfoAll();

    return ResponseEntity.ok(managerDtoList);
  }

  @Operation(summary = "Get manager detail list", description = "Get manager list with all details")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved data", content = @Content(schema = @Schema(implementation = List.class))),
  })
  @GetMapping("/all/admin")
  public ResponseEntity<List<Manager>> getAll(){
    List<Manager> managerList = managerService.getAll();

    return ResponseEntity.ok(managerList);
  }

  @Operation(summary = "Create manager", description = "This method must be used to create new manager in the system")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully created data", content = @Content(schema = @Schema(implementation = ManagerDto.class))),
  })
  @PostMapping
  public ResponseEntity<ManagerDto> createManager(@RequestBody ManagerDto managerDto) {
    ManagerDto createdManagerDto = managerService.create(managerDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdManagerDto);
  }

  @Operation(summary = "Update manager", description = "This method must be used to update existing manager in the system")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully created data", content = @Content(schema = @Schema(implementation = ManagerDto.class))),
          @ApiResponse(responseCode = "404", description = "Resource not found")
  })
  @PutMapping("/{id}")
  public ResponseEntity<ManagerDto> updateManager(@PathVariable UUID id, @RequestBody ManagerDto managerDto) {
    ManagerDto updatedManager = managerService.update(id, managerDto);
    if (updatedManager == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedManager);
  }

}
