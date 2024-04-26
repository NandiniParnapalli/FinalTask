package com.kog.emp.controller;

import com.kog.emp.dto.EmployeeDto;
import com.kog.emp.dto.EmployeeResponse;
import com.kog.emp.entity.Employee;
import com.kog.emp.exception.EmployeeNotFoundException;
import com.kog.emp.service.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import com.kog.emp.config.AppConstants;
@SecurityRequirement(name = "EmployeeManagement")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/auth")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @PostMapping("/admin/save")
    public String saveEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);
    }

    @GetMapping("/adminuser/employees")
    public EmployeeResponse getAllEmployees(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_EMPLOYEES_BY, required = false) String sortBy,
                                            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        return  employeeService.getAllEmployees(pageNumber, pageSize, sortBy, sortOrder);


    }
    @GetMapping("/user/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(employeeId);
    }
    @DeleteMapping("/adminuser/{id}")
    public String deleteEmployee(@PathVariable("id") Integer employeeId) throws EmployeeNotFoundException {
        return employeeService.deleteEmployee(employeeId);
    }
    @PutMapping("/user/{employeeId}")
    public String updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee) throws EmployeeNotFoundException {
        return employeeService.updateEmployee(employeeId,employee);
    }
}
