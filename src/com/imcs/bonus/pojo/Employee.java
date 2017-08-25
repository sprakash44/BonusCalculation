package com.imcs.bonus.pojo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import org.apache.log4j.Logger;

public class Employee implements Serializable{
	final static Logger logger=Logger.getLogger(Employee.class);
	
	private static final long serialVersionUID = 8200640320344236964L;
	private int empNo;
	private int deptNo;
	private Date doj;
	private Date dob;
	private float salary;
	private int salGrade;
	
	public Employee(){
		
	}
	public Employee(int empNo, int deptNo, Date doj, Date dob, float salary, int salGrade) {
		super();
		this.empNo = empNo;
		this.deptNo = deptNo;
		this.doj = doj;
		this.dob = dob;
		this.salary = salary;
		this.salGrade = salGrade;
	}
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public int getSalGrade() {
		return salGrade;
	}
	public void setSalGrade(int salGrade) {
		this.salGrade = salGrade;
	}

	public static class EmployeeAgeComparator implements Comparator<Employee>{
		
		@Override
		public int compare(Employee o1, Employee o2) {
			if(o1.getDob().before(o2.getDob()))
					return 1;
			else if(o1.getDob().after(o2.getDob()))
				return -1;
			else
				return 0;
				
		}
	}
	
	public static class EmployeeJoiningComparator implements Comparator<Employee>{

			@Override
			public int compare(Employee o1, Employee o2) {
				if(o1.getDoj().before(o2.getDoj()))
					return 1;
			else if(o1.getDoj().after(o2.getDoj()))
				return -1;
			else
				return 0;
			}
		}
}
