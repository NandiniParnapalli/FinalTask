package com.kog.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

 @Data
@NoArgsConstructor
@AllArgsConstructor
 public class EmployeeResponse {

    private List<EmployeeDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;

}

