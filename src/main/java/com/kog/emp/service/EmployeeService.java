package com.kog.emp.service;

import com.kog.emp.dto.EmployeeDto;
import com.kog.emp.dto.EmployeeResponse;
import com.kog.emp.entity.Employee;
import com.kog.emp.exception.EmployeeNotFoundException;

public interface EmployeeService {
    /**
     *
     * @param employeeDto it accepts employeeDto object as an argument
     * @return returns
     */
     String saveEmployee(EmployeeDto employeeDto);

    /**
     *
     * @param pageNumber it accepts pageNumber as int type
     * @param pageSize it accepts pageNumber as int type
     * @param sortBy it accepts pageNumber as String type
     * @param sortOrder it accepts pageNumber as String type
     * @return returns list of employees including pagination and sorting
     */
     EmployeeResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    /**
     *
     * @param employeeId accept employeeId as argument
     * @return returns an employee object
     * @throws EmployeeNotFoundException throws an exception
     */
     Employee getEmployeeById(Integer employeeId) throws EmployeeNotFoundException;

    /**
     *
     * @param employeeId accept employeeId as argument
     * @return  returns string value
     * @throws EmployeeNotFoundException throws an exception
     */
     String deleteEmployee(Integer employeeId) throws EmployeeNotFoundException;

    /**
     *
     * @param employeeId required to give employeeId
     * @param employee required to give employee object
     * @return returns string value
     * @throws EmployeeNotFoundException throws an exception
     */
     String updateEmployee(Integer employeeId,Employee employee) throws EmployeeNotFoundException;

}
