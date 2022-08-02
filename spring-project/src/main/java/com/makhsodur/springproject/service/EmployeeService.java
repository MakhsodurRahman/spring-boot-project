package com.makhsodur.springproject.service;

import com.makhsodur.springproject.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();
    public Employee findById(int id);

    public Employee save(Employee employee);
    public void deleteById(int id);

    public Employee getNames(String firstName, String lastName);

    public List<Employee> getNamesUseOr(String firstName, String lastName);
}
