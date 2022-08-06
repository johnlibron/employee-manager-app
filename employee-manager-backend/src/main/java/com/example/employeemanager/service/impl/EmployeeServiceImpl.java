package com.example.employeemanager.service.impl;

import com.example.employeemanager.data.dto.EmployeeDto;
import com.example.employeemanager.data.dto.EmployeeIncomingDto;
import com.example.employeemanager.data.mapper.EmployeeMapper;
import com.example.employeemanager.data.model.Employee;
import com.example.employeemanager.data.dao.EmployeeRepository;
import com.example.employeemanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public EmployeeDto addEmployee(EmployeeIncomingDto employeeIncomingDto) {
        Employee employee = EmployeeMapper.INSTANCE.toModel(employeeIncomingDto);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        Employee addedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.toDto(addedEmployee);
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeMapper.INSTANCE.toDtoList(employees);
    }

    @Transactional
    @Override
    public Optional<EmployeeDto> updateEmployee(Long id, EmployeeIncomingDto employeeIncomingDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }
        Employee employee = EmployeeMapper.INSTANCE.toModel(employeeIncomingDto, optionalEmployee.get());
        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.toDto(updatedEmployee);
        return Optional.of(employeeDto);
    }

    @Override
    public Optional<EmployeeDto> findEmployee(Long id) {
        // employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.toDto(optionalEmployee.get());
        return Optional.of(employeeDto);
    }

    @Transactional
    @Override
    public Optional<Long> deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }
        employeeRepository.deleteById(id);
        return Optional.of(id);
    }
}
