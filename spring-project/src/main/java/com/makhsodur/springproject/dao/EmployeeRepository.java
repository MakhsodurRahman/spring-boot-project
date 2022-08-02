package com.makhsodur.springproject.dao;

import com.makhsodur.springproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByFirstNameAndLastName(String firstName,String lastName);
}
