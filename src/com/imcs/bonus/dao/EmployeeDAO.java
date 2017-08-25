package com.imcs.bonus.dao;

import java.util.List;

import com.imcs.bonus.pojo.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployeesByAge(int deptNo);
	public List<Employee> getEmployeesByJoining(int deptNo);
	public void loadEmployee(List<Employee> employees);
	
	
}
