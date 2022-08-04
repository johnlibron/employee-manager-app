package com.example.employeemanager.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIncomingDto {

    @NotEmpty
    @NotBlank
    private String name;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @NotEmpty
    @NotBlank
    private String jobTitle;

    @NotEmpty
    @NotBlank
    private String phone;

    @NotEmpty
    @NotBlank
    private String imageUrl;
}
