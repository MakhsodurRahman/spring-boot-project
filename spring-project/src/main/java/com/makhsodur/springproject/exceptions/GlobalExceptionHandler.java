package com.makhsodur.springproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler
    public ResponseEntity<EmployeeResponse> handleException(EmployeeNotFoundException ex){
        EmployeeResponse error = new EmployeeResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<EmployeeResponse> handleException(Exception ex){
        EmployeeResponse error = new  EmployeeResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("type mismatch  ");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
