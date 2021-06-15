package com.neokred.emailCommunication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.neokred.emailCommunication.models.Employee;

@Service
public class EmailNotificationService {

	@Value("${employeeService.path}")
	private String employeeServicePath;

	@Autowired
	private RestTemplate restTemplate;

	public List<String> getAllEmployeesFromEmployeeService() {
		ResponseEntity<Employee[]> employeesResponseEntity = restTemplate.getForEntity(employeeServicePath,
				Employee[].class);

		Employee[] employeesArray = employeesResponseEntity.getBody();
		return Arrays.stream(employeesArray).map(Employee::getEmail).collect(Collectors.toList());

	}

	public Employee getEmployeeFromEmployeeService(String employeeId) throws Exception {
		ResponseEntity<Employee> employeeResponseEntity = null;
		try {
			employeeResponseEntity = restTemplate.getForEntity(employeeServicePath + employeeId, Employee.class);
			Employee employee = employeeResponseEntity.getBody();
			return employee;
		} catch (Exception ex) {
			throw new Exception("Error hitting the EmployeeService API");
		}

	}

	public void sendMailToEmployees(String employeeId) throws Exception {
		List<String> toEmails = new ArrayList<>();
		Employee employee;

		employee = getEmployeeFromEmployeeService(employeeId);
		toEmails.add(employee.getEmail());
		sendMail("Subject", "Body", toEmails);

	}

	public void sendMailToEmployees() {
		List<String> toEmails = getAllEmployeesFromEmployeeService();

		sendMail("Subject", "Body", toEmails);

	}

	public void sendMail(String subject, String body, List<String> toEmailIds) {
		// send mail logic to be coded here
		System.out.println("Sending emails for");
		for (String emailID : toEmailIds) {
			System.out.println(emailID);
		}
	}
}
