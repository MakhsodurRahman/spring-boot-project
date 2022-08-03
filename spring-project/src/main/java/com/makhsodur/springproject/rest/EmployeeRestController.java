package com.makhsodur.springproject.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.makhsodur.springproject.entity.Employee;
import com.makhsodur.springproject.exceptions.EmployeeNotFoundException;
import com.makhsodur.springproject.request.CreateEmployeeRequest;
import com.makhsodur.springproject.response.EmployeesResponse;
import com.makhsodur.springproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
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
	public List<EmployeesResponse> findAll(){


		List<Employee> employeeList = employeeService.findAll();
		List<EmployeesResponse> responseList = new  ArrayList<>();
		employeeList.stream().forEach(employee -> {
			responseList.add(new EmployeesResponse(employee));
		});
		return responseList;

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
	public ResponseEntity<CreateEmployeeRequest> saveEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
		Employee employee = new Employee(createEmployeeRequest);

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

	@PutMapping("/updateEmployee/{id}/{firstName}")
	public String updateEmployee(@PathVariable int id, @PathVariable String firstName){
		return employeeService.updateEmployeee(id,firstName)+" employee updated";
	}

	@GetMapping("/pagination")
	public List<Employee> pagination(@RequestParam int pageNo, @RequestParam int pageSize){
		return employeeService.getAllEmployeePagination(pageNo,pageSize);

//		List<EmployeeResponse> employees = new ArrayList<>();
//
//		employeeList.stream().forEach(student -> {
//			employees.add(new EmployeeResponse(student));
//		});

//		return employeeList;

	}

	@GetMapping("/sorting")
	public List<Employee> sortingEmployee(){
		return employeeService.sortedEmploye();
	}

}










