package com.example.employeemanager.service;

import com.example.employeemanager.data.dto.EmployeeDto;
import com.example.employeemanager.data.dto.EmployeeIncomingDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeIncomingDto employeeIncomingDto);

    List<EmployeeDto> findAllEmployees();

    Optional<EmployeeDto> updateEmployee(Long id, EmployeeIncomingDto employeeIncomingDto);

    Optional<EmployeeDto> findEmployee(Long id);

    Optional<Long> deleteEmployee(Long id);
}
