package com.makhsodur.springproject.dao;

import java.util.List;

import com.makhsodur.springproject.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
		
}
