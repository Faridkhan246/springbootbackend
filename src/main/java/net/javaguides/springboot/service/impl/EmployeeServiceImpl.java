package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;
 
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}



	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Employee","Id",id);
		}
	}



	@Override
	public Employee updateEmployee(Employee employee, long id) {
	//we need to check weather employee with given id is exist in Db or not
Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
	()->new ResourceNotFoundException("Employee","Id",id));
existingEmployee.setFirstName(employee.getFirstName());
existingEmployee.setLastName(employee.getLastName());
existingEmployee.setEmail(employee.getEmail());
//save existing employee to DB
employeeRepository.save(existingEmployee);
return existingEmployee; 
}
	@Override
	public void deleteEmployee(long id) {
		//check wheather a employee exist in a Db or not
employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
employeeRepository.deleteById(id);
		
	}
	
	

}
