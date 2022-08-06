package com.example.employeemanager.controller;

import com.example.employeemanager.data.dto.EmployeeDto;
import com.example.employeemanager.data.dto.EmployeeIncomingDto;
import com.example.employeemanager.data.mapper.EmployeeMapper;
import com.example.employeemanager.data.model.Employee;
import com.example.employeemanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EmployeeDto> list = EmployeeMapper.INSTANCE.convert(employees);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long id) {
        Optional<Employee> employee = employeeService.findEmployee(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.convert(employee.get());
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeIncomingDto employeeIncomingDto) {
        Employee employee = EmployeeMapper.INSTANCE.convert(employeeIncomingDto);
        Employee addedEmployee = employeeService.addEmployee(employee);
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.convert(addedEmployee);
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
        Employee employee = EmployeeMapper.INSTANCE.convert(employeeIncomingDto, optionalEmployee.get());
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.convert(updatedEmployee);
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
