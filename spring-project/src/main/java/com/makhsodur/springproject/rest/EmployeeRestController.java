package com.makhsodur.springproject.rest;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import com.makhsodur.springproject.entity.Employee;
import com.makhsodur.springproject.exceptions.EmployeeNotFoundException;
import com.makhsodur.springproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	@Autowired
	private MessageSource messageSource;

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);
		if(employee == null){
			throw new EmployeeNotFoundException("employeeId not found : "+employeeId);
		}
		return employee;
	}


	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		Employee employees =  employeeService.save(employee);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{employeeId}")
				.buildAndExpand(employees.getId()).toUri();
		return  ResponseEntity.created(location).build();// ei section er kaj holo postman e 201 create status deya
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
			throw new EmployeeNotFoundException("employeeId not found : "+employeeId);
		}
		employeeService.deleteById(employeeId);
		return "employee deleted : 	"+ employeeId;
	}

	@GetMapping("/getNames/{firstName}/{lastName}")
	public Employee getNames(@PathVariable String firstName, @PathVariable String lastName){
		return employeeService.getNames(firstName,lastName);
	}

	@GetMapping("/getNamesUseOr/{firstName}/{lastName}")
	public List<Employee> getNamesUseOr(@PathVariable String firstName, @PathVariable String lastName){
		List<Employee> list= employeeService.getNamesUseOr(firstName,lastName);
		return list;
	}



}










