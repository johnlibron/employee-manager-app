package com.example.employeemanager.data.mapper;

import com.example.employeemanager.data.dto.EmployeeDto;
import com.example.employeemanager.data.dto.EmployeeIncomingDto;
import com.example.employeemanager.data.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto convert(Employee employee);

    Employee convert(EmployeeIncomingDto employeeIncomingDto);

    List<EmployeeDto> convert(List<Employee> employees);

    @Mapping(source = "employee.id", target = "id")
    @Mapping(source = "employeeIncomingDto.name", target = "name")
    @Mapping(source = "employeeIncomingDto.email", target = "email")
    @Mapping(source = "employeeIncomingDto.jobTitle", target = "jobTitle")
    @Mapping(source = "employeeIncomingDto.phone", target = "phone")
    @Mapping(source = "employeeIncomingDto.imageUrl", target = "imageUrl")
    @Mapping(source = "employee.employeeCode", target = "employeeCode")
    Employee convert(EmployeeIncomingDto employeeIncomingDto, Employee employee);
}
