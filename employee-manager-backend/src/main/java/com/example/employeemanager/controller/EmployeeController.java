package com.example.employeemanager.controller;

import com.example.employeemanager.data.dto.EmployeeDto;
import com.example.employeemanager.data.dto.EmployeeIncomingDto;
import com.example.employeemanager.data.model.Employee;
import com.example.employeemanager.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final ModelMapper modelMapper;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EmployeeDto> list = employees
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long id) {
        Optional<Employee> employee = employeeService.findEmployee(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeIncomingDto employeeIncomingDto) {
        Employee employee = modelMapper.map(employeeIncomingDto, Employee.class);
        Employee addedEmployee = employeeService.addEmployee(employee);
        EmployeeDto employeeDto = modelMapper.map(addedEmployee, EmployeeDto.class);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable("employeeId") Long id,
            @Valid @RequestBody EmployeeIncomingDto employeeIncomingDto) {
        Optional<Employee> optionalEmployee = employeeService.findEmployee(id);
        if (optionalEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(employeeIncomingDto, optionalEmployee.get());
        Employee updatedEmployee = employeeService.updateEmployee(optionalEmployee.get());
        EmployeeDto employeeDto = modelMapper.map(updatedEmployee, EmployeeDto.class);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Long id) {
        Optional<Employee> employee = employeeService.findEmployee(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
