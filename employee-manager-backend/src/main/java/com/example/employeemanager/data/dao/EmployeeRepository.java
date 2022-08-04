package com.example.employeemanager.data.dao;

import com.example.employeemanager.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
