package com.makhsodur.springproject.exceptions;

import lombok.Data;

@Data
public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }
}
