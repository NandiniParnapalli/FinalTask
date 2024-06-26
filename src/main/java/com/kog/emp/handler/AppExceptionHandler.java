package com.kog.emp.handler;

import com.kog.emp.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public Map<String,String> handleException(EmployeeNotFoundException ex){
        Map<String,String> errors=new HashMap<>();
        errors.put("error message", ex.getMessage());
        return errors;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return errors;
    }
}
