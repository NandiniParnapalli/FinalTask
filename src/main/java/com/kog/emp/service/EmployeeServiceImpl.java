package com.kog.emp.service;


import com.kog.emp.dto.EmployeeDto;
import com.kog.emp.dto.EmployeeResponse;
import com.kog.emp.entity.Employee;
import com.kog.emp.exception.EmployeeNotFoundException;
import com.kog.emp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;


    private final ModelMapper modelMapper;
    @Override
    public String saveEmployee(EmployeeDto employeeDto){
     Employee emp=Employee.build(0,employeeDto.getEmployeeName(),employeeDto.getEmail(),employeeDto.getPassword(),employeeDto.getDesignation(),employeeDto.getContactNo(),employeeDto.getSalary());
         employeeRepository.save(emp);
         log.info("saving employee data");
       return "Employee Saved Successfully";
    }

    //Listing the employee details through pagination and sorting
    @Override
    public EmployeeResponse getAllEmployees(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder){
      log.info("Getting all employees");


        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Employee> pageEmployees = employeeRepository.findAll(pageDetails);

        List<Employee> employees= pageEmployees.getContent();

        List<EmployeeDto> employeeDtos = employees.stream().map(employee-> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        EmployeeResponse employeeResponse=new EmployeeResponse();

        employeeResponse.setContent(employeeDtos);
        employeeResponse.setPageNumber( pageEmployees.getNumber());
        employeeResponse.setPageSize(pageEmployees.getSize());
        employeeResponse.setTotalElements( pageEmployees.getTotalElements());
        employeeResponse.setTotalPages( pageEmployees.getTotalPages());
        employeeResponse.setLastPage( pageEmployees.isLast());

        return employeeResponse;
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) throws EmployeeNotFoundException {
       Employee emp=employeeRepository.findById(employeeId).orElse(null);
       if(emp!=null)
       {
           log.debug("Employee present");
           return emp;
       }
       log.warn("Employee not present..check once");
        throw new EmployeeNotFoundException("Employee Not Found");
    }
    //deleting the employee data from db based on id
    @Override
    public String deleteEmployee(Integer employeeId) throws EmployeeNotFoundException {
        Employee emp=employeeRepository.findById(employeeId).orElse(null);
        if(emp!=null) {
            log.info("Deleting the employee data");
            employeeRepository.delete(emp);
            return "Employee Deleted";
        }
        log.error("Employee not exist");
       throw new EmployeeNotFoundException("Employee Not Found");
    }
    /* updating the employee details with the reference of id
    and throws an exception when employee not exists in db
     */
    @Override
    public String updateEmployee(Integer employeeId,Employee employee) throws EmployeeNotFoundException {

        Employee emp=employeeRepository.findById(employeeId).orElse(null);
        if(emp!=null) {
            log.debug("updating the employee data");
            employeeRepository.save(employee);
            return "Employee Updated";
        }
        log.warn("Employee not exist try once");
        throw new EmployeeNotFoundException("Employee Not Found");
    }
}
