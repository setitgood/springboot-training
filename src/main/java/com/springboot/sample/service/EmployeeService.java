package com.springboot.sample.service;

import java.util.List;

import com.springboot.sample.model.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	List<Employee> findByEmpNo(String empNo);
	
	void deletebyEmpNo(String empNo);
	
	Employee updEmployee(Employee emp);

}
