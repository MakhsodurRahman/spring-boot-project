package com.makhsodur.springproject.dao;

import com.makhsodur.springproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import javax.persistence.criteria.CriteriaBuilder;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
