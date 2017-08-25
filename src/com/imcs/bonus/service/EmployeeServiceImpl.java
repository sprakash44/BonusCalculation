package com.imcs.bonus.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.bonus.dao.BonusDAO;
import com.imcs.bonus.dao.BonusDAOImpl;
import com.imcs.bonus.dao.EmployeeDAO;
import com.imcs.bonus.dao.EmployeeDAOImpl;
import com.imcs.bonus.pojo.Bonus;
import com.imcs.bonus.pojo.Employee;

public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeDAO eDimpl=new EmployeeDAOImpl();
	private EmployeeDAO employeeDao=new EmployeeDAOImpl();

	public List<Employee> getEmployees(int deptNo){
		if(deptNo%2==0)
			return employeeDao.getEmployeesByAge(deptNo);
		else
			return employeeDao.getEmployeesByJoining(deptNo);
	}
	
	public void loadEmployee(List<Employee> employes){
		eDimpl.loadEmployee(employes);
	}
}
