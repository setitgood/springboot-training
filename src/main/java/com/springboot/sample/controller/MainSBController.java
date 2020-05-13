package com.springboot.sample.controller;

import java.util.List;

import com.springboot.sample.dao.EmployeeDAO;
import com.springboot.sample.model.Employee;
import com.springboot.sample.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class MainSBController {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private EmployeeService employeeservice;
	
	@RequestMapping("/")
	public String welcome() {
		return "Welcome to RestTemplate Demo.";
	}

	@RequestMapping(value = "listEmployees", method = RequestMethod.GET)
	public List<Employee> ListEmployees(){
		return employeeservice.findAll();
	}
	
	@RequestMapping(value = "employee/{empNo}", method = RequestMethod.GET)
	public List<Employee> getEmployee1(@PathVariable("empNo") String empNo){
		return employeeservice.findByEmpNo(empNo);
	}
	
	@RequestMapping(value = "deleteemployee/{empNo}", method = RequestMethod.POST)
	public String delEmployee(@PathVariable("empNo") String empNo){
		employeeservice.deletebyEmpNo(empNo);
		return "Employee Deleted " + empNo;
	}
	
	@RequestMapping(value = "updateemployee1", method = RequestMethod.PUT)
	public Employee updateEmployee1(@RequestBody Employee emp) {
		 System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
		return employeeservice.updEmployee(emp);
	}
	
	// URL - http://localhost:8080/employees
	@RequestMapping(value = "/employees", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Employee> getEmployees() {
		List<Employee> list = employeeDAO.getAllEmployees();
		return list;
	}

	// URL - http://localhost:8080/employee/{empNo}
	@RequestMapping(value = "/employee/{empNo}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		return employeeDAO.getEmployee(empNo);
	}
	
	// URL - http://localhost:8080/employee
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE })

	//@ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {
        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
        return employeeDAO.updateEmployee(emp);
    }
    
 // URL - http://localhost:8080/deleteemployee/{empNo}
	@RequestMapping(value = "/deleteemployee/{empNo}", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public String deleteEmployee(@PathVariable("empNo") String empNo) {
		employeeDAO.deleteEmployee(empNo);
		return "Employee Deleted";
	}
}