package com.imcs.bonus.service;

import java.util.List;

import com.imcs.bonus.pojo.Bonus;
import com.imcs.bonus.pojo.Employee;

public interface EmployeeService {
	public List<Employee> getEmployees(int deptNo);
	public void loadEmployee(List<Employee> empList);
	
}
