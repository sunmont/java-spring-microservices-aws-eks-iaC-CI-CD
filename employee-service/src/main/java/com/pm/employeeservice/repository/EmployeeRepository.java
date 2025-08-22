package com.pm.employeeservice.repository;

import com.pm.employeeservice.model.Employee;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
  boolean existsByEmail(String email);
  boolean existsByEmailAndIdNot(String email, UUID id);
}
