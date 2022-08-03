package com.makhsodur.springproject.service;

import com.makhsodur.springproject.entity.Employee;
import com.makhsodur.springproject.request.CreateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();
    public Employee findById(int id);

    public Employee save(Employee employee);


    public void deleteById(int id);

    public Employee getNames(String firstName, String lastName);

    public List<Employee> getNamesUseOr(String firstName, String lastName);

    public Integer updateEmployeee(int id, String firstName);

    public List<Employee> getAllEmployeePagination(int pageNo, int pageSize);

    public List<Employee> sortedEmploye();
}
