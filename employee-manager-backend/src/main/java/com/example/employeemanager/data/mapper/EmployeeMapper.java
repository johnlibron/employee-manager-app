package com.example.employeemanager.data.mapper;

import com.example.employeemanager.data.dto.EmployeeDto;
import com.example.employeemanager.data.dto.EmployeeIncomingDto;
import com.example.employeemanager.data.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto toDto(Employee employee);

    Employee toModel(EmployeeIncomingDto employeeIncomingDto);

    List<EmployeeDto> toDtoList(List<Employee> employees);

    @Mappings({
        @Mapping(source = "employee.id", target = "id"),
        @Mapping(source = "employeeIncomingDto.name", target = "name"),
        @Mapping(source = "employeeIncomingDto.email", target = "email"),
        @Mapping(source = "employeeIncomingDto.jobTitle", target = "jobTitle"),
        @Mapping(source = "employeeIncomingDto.phone", target = "phone"),
        @Mapping(source = "employeeIncomingDto.imageUrl", target = "imageUrl"),
        @Mapping(source = "employee.employeeCode", target = "employeeCode")
    })
    Employee toModel(EmployeeIncomingDto employeeIncomingDto, Employee employee);
}
