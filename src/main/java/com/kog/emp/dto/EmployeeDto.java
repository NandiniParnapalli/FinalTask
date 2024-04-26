package com.kog.emp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EmployeeDto {
    @Valid
    @NotNull(message = "please fill the field")
    private String employeeName;
    @Email
    private String email;
    @Pattern(regexp = "^[a-zA-Z0-9]*$",message = "it should be the combination of characters,numbers")
    private String password;
    @NotBlank
    private String designation;
    @Pattern(regexp = "^\\d{10}$",message = "it accepts numbers with size 10")
    private String contactNo;
    @NotBlank(message = "please give the salary details")
    private String salary;

}
