package com.imcs.bonus.pojo;

import java.util.Date;

import org.apache.log4j.Logger;

public class EmployeeBonus {
	final static Logger logger=Logger.getLogger(EmployeeBonus.class);
	private int empNo;
	private String status;
	private float amount;
	private Date dateAllocated;
	
	public EmployeeBonus(int empNo, String status, float amount, Date dateAllocated) {
		super();
		this.empNo = empNo;
		this.status = status;
		this.amount = amount;
		this.dateAllocated = dateAllocated;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getDateAllocated() {
		return dateAllocated;
	}
	public void setDateAllocated(Date dateAllocated) {
		this.dateAllocated = dateAllocated;
	}
}
