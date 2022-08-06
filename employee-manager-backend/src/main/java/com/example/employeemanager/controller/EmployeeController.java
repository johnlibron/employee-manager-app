package com.example.employeemanager.controller;

import com.example.employeemanager.data.dto.EmployeeDto;
import com.example.employeemanager.data.dto.EmployeeIncomingDto;
import com.example.employeemanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
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
        List<EmployeeDto> employees = employeeService.findAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long id) {
        final Optional<EmployeeDto> employee = employeeService.findEmployee(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeIncomingDto employeeIncomingDto) {
        final EmployeeDto addedEmployee = employeeService.addEmployee(employeeIncomingDto);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable("employeeId") Long id,
            @Valid @RequestBody EmployeeIncomingDto employeeIncomingDto) {
        final Optional<EmployeeDto> updatedEmployee = employeeService.updateEmployee(id, employeeIncomingDto);
        if (updatedEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEmployee.get(), HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long id) {
        final Optional<Long> deletedEmployee = employeeService.deleteEmployee(id);
        if (deletedEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
