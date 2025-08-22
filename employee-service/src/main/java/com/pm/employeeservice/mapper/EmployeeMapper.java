package com.pm.employeeservice.mapper;

import com.pm.employeeservice.dto.EmployeeRequestDTO;
import com.pm.employeeservice.dto.EmployeeResponseDTO;
import com.pm.employeeservice.model.Employee;
import java.time.LocalDate;

public class EmployeeMapper {
  public static EmployeeResponseDTO toDTO(Employee patient) {
    EmployeeResponseDTO patientDTO = new EmployeeResponseDTO();
    patientDTO.setId(patient.getId().toString());
    patientDTO.setName(patient.getName());
    patientDTO.setAddress(patient.getAddress());
    patientDTO.setEmail(patient.getEmail());
    patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

    return patientDTO;
  }

  public static Employee toModel(EmployeeRequestDTO patientRequestDTO) {
    Employee patient = new Employee();
    patient.setName(patientRequestDTO.getName());
    patient.setAddress(patientRequestDTO.getAddress());
    patient.setEmail(patientRequestDTO.getEmail());
    patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
    patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
    return patient;
  }
}
