package com.pm.employeeservice.controller;

import com.pm.employeeservice.dto.EmployeeRequestDTO;
import com.pm.employeeservice.dto.EmployeeResponseDTO;
import com.pm.employeeservice.dto.validators.CreateEmployeeValidationGroup;
import com.pm.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee", description = "API for managing Employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  @Operation(summary = "Get Employees")
  public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
    List<EmployeeResponseDTO> employees = employeeService.getEmployees();
    return ResponseEntity.ok().body(employees);
  }

  @PostMapping
  @Operation(summary = "Create a new Employee")
  public ResponseEntity<EmployeeResponseDTO> createEmployee(
      @Validated({Default.class, CreateEmployeeValidationGroup.class})
      @RequestBody EmployeeRequestDTO employeeRequestDTO) {

    EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(
            employeeRequestDTO);

    return ResponseEntity.ok().body(employeeResponseDTO);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a new Employee")
  public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable UUID id,
      @Validated({Default.class}) @RequestBody EmployeeRequestDTO employeeRequestDTO) {

    EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(id,
            employeeRequestDTO);

    return ResponseEntity.ok().body(employeeResponseDTO);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a Employee")
  public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
    employeeService.deleteEmployee(id);
    return ResponseEntity.noContent().build();
  }
}
