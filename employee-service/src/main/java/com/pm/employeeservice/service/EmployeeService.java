package com.pm.employeeservice.service;

import com.pm.employeeservice.dto.EmployeeRequestDTO;
import com.pm.employeeservice.dto.EmployeeResponseDTO;
import com.pm.employeeservice.exception.EmailAlreadyExistsException;
import com.pm.employeeservice.exception.EmployeeNotFoundException;
import com.pm.employeeservice.kafka.KafkaProducer;
import com.pm.employeeservice.mapper.EmployeeMapper;
import com.pm.employeeservice.model.Employee;
import com.pm.employeeservice.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final KafkaProducer kafkaProducer;

  public EmployeeService(EmployeeRepository employeeRepository,
                         KafkaProducer kafkaProducer) {
    this.employeeRepository = employeeRepository;
    this.kafkaProducer = kafkaProducer;
  }

  public List<EmployeeResponseDTO> getEmployees() {
    List<Employee> employees = employeeRepository.findAll();

    return employees.stream().map(EmployeeMapper::toDTO).toList();
  }

  public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
    if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
      throw new EmailAlreadyExistsException(
          "A employee with this email " + "already exists"
              + employeeRequestDTO.getEmail());
    }

    Employee newEmployee = employeeRepository.save(
        EmployeeMapper.toModel(employeeRequestDTO));

    kafkaProducer.sendEvent(newEmployee);

    return EmployeeMapper.toDTO(newEmployee);
  }

  public EmployeeResponseDTO updateEmployee(UUID id,
                                           EmployeeRequestDTO employeeRequestDTO) {

    Employee employee = employeeRepository.findById(id).orElseThrow(
        () -> new EmployeeNotFoundException("Patient not found with ID: " + id));

    if (employeeRepository.existsByEmailAndIdNot(employeeRequestDTO.getEmail(),
        id)) {
      throw new EmailAlreadyExistsException(
          "A patient with this email " + "already exists"
              + employeeRequestDTO.getEmail());
    }

    employee.setName(employeeRequestDTO.getName());
    employee.setAddress(employeeRequestDTO.getAddress());
    employee.setEmail(employeeRequestDTO.getEmail());
    employee.setDateOfBirth(LocalDate.parse(employeeRequestDTO.getDateOfBirth()));

    Employee updatedEmployee = employeeRepository.save(employee);
    return EmployeeMapper.toDTO(updatedEmployee);
  }

  public void deleteEmployee(UUID id) {
    employeeRepository.deleteById(id);
  }
}
