package com.springboot.sample.dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.springboot.sample.model.Employee;
import org.springframework.stereotype.Repository;
 
@Repository
public class EmployeeDAO {
 
    private static final Map<String, Employee> empMap = new HashMap<String, Employee>();
 
    static {
        initializeEmployees();
    }

    // initialize employee list
    private static void initializeEmployees() {
        Employee emp1 = new Employee("01", "Gods", "Toni");
        empMap.put(emp1.getEmpNo(), emp1);
    }

    // return employee detail
    public Employee getEmployee(String empNo) {
        return empMap.get(empNo);
    }

    // add employee
    public Employee addEmployee(Employee emp) {
        empMap.put(emp.getEmpNo(), emp);
        return emp;
    }

    //update employee
    public Employee updateEmployee(Employee emp) {
        empMap.put(emp.getEmpNo(), emp);
        return emp;
    }

    //delete employee
    public void deleteEmployee(String empNo) {
        empMap.remove(empNo);
    }
 
    public List<Employee> getAllEmployees() {
        Collection<Employee> c = empMap.values();
        List<Employee> list = new ArrayList<Employee>();
        list.addAll(c);
        return list;
    }
 
}