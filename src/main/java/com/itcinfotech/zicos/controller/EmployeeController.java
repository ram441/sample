/*package com.itcinfotech.zicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.model.Employee;
import com.itcinfotech.zicos.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value="employee", method = RequestMethod.GET)
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
}
*/