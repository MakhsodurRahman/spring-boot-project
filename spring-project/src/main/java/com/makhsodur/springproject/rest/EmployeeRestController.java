package com.makhsodur.springproject.rest;

import java.util.List;

import com.makhsodur.springproject.entity.Employee;
import com.makhsodur.springproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);
		if(employee == null){
			throw new RuntimeException("id not found");
		}
		return employee;
	}

	@PostMapping("/employees")
	public Employee saveEmploye(@RequestBody Employee employee){
		Employee employees =  employeeService.save(employee);
		return  employees;
	}
	@PutMapping("/employees")
	public Employee updateCustomer(@RequestBody Employee employee){
		Employee employees =  employeeService.save(employee);
		return  employees;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);

		if(employee == null){
			throw new RuntimeException("id not found nai ken");
		}
		employeeService.deleteById(employeeId);
		return "employee deleted : 	"+ employeeId;
	}

	
}










