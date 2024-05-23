package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService=employeeService;
	}

	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
	}
	//build all employee rest api
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	//build get employee by id rest api
	//http://localhost:8080/api/employees/1
	@GetMapping ("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable ("id")long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
		}
	//build update employee Rest Api
	//http://localhost:8080/api/employees/1
	@PutMapping("{id}")
	public ResponseEntity<Employee>updateEmployee(@PathVariable("id")long id,
			@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
		
	}
	
}
