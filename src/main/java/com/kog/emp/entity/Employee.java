package com.kog.emp.entity;

import jakarta.persistence.*;


import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nandini
 * @since 06-04-2024
 *  @version 1.0
 * @see <a href="http://localhost:8090/swagger-ui/index.html">swagger document</a>
 *
 */
@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Valid

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "please fill the field")
    private Integer employeeId;
    private String employeeName;
    private String email;
    private String password;
    private String designation;
    private String contactNo;
    private String salary;



}
