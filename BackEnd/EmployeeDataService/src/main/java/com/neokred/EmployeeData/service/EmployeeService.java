package com.neokred.EmployeeData.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neokred.EmployeeData.model.Employee;
import com.neokred.EmployeeData.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final static String EMAIL_DOMAIN = "@neokred.tech";

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		int numberOfSimilarNamedEmployee = employeeRepository
				.getExisitingEmployeeCountWithSameFullName(employee.getFirstName().toLowerCase(), employee.getLastName().toLowerCase());
		generateEmployeeEmail(employee, numberOfSimilarNamedEmployee);

		return employeeRepository.save(employee);
	}

	private void generateEmployeeEmail(Employee employee, int numberOfSimilarNamedEmployee) {
		// get employee email based on latest value in DB
		String employeeEmail = employee.getFirstName().toLowerCase() + "." + employee.getLastName().toLowerCase();
		if (numberOfSimilarNamedEmployee > 0) {
			employeeEmail += numberOfSimilarNamedEmployee;
		}
		employeeEmail += EMAIL_DOMAIN;
		employee.setEmail(employeeEmail);
	}
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employee -> employees.add(employee));
		return employees;
	}
	
	public Employee getEmployee(String employeeId) {
		return employeeRepository.findById(employeeId).get();
		
	}

}
