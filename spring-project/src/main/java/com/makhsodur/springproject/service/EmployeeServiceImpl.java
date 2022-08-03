package com.makhsodur.springproject.service;
import com.makhsodur.springproject.dao.AddressRepository;
import com.makhsodur.springproject.dao.EmployeeRepository;
import com.makhsodur.springproject.entity.Employee;
import com.makhsodur.springproject.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private AddressRepository addressRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {


        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;
        if (result.isPresent()){
            employee = result.get();
        }
        else {
            throw new EmployeeNotFoundException("employee not found : ");
        }
        return employee;
    }



    @Override
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getNames(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<Employee> getNamesUseOr(String firstName, String lastName) {
        return employeeRepository.getByFirstNameOrLastName(firstName,lastName);
    }

    @Override
    public Integer updateEmployeee(int id, String firstName){
        return employeeRepository.updateFirstName(id,firstName);
    }

    @Override
    public List<Employee> getAllEmployeePagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1,pageSize);
        return employeeRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Employee> sortedEmploye() {
        Sort sort = Sort.by(Sort.Direction.DESC,"firstName");
        return employeeRepository.findAll(sort);
    }
}
