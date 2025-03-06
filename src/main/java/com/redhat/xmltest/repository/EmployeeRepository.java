package com.redhat.xmltest.repository;

import com.redhat.xmltest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can define custom queries if needed, for example:
    // List<Employee> findByRole(String role);
}
