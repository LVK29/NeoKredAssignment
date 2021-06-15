package com.neokred.emailCommunication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neokred.emailCommunication.service.EmailNotificationService;

@RestController
@RequestMapping("/nofity/email")
public class EmailNotificationController {

	@Autowired
	EmailNotificationService emailNotificationService;

	@GetMapping
	public void sendEmployeeEmail() {

		emailNotificationService.sendMailToEmployees();

	}

	@GetMapping(path = "/{employeeId}")
	public void getEmployeeEmail(@PathVariable String employeeId) throws Exception {

		emailNotificationService.sendMailToEmployees(employeeId);

	}
}
