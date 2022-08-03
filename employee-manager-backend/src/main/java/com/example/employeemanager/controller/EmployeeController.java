package com.example.employeemanager.controller;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.service.EmployeeService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") Long id) {
        Employee employee = employeeService.findEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee addedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
