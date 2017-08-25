package com.imcs.bonus.service;

import com.imcs.bonus.pojo.Employee;

public interface EmployeeBonusService {
	
	public float assignBonus(Employee emp, float remainingAmount) ;	
	public void allocateBonus();
	
}
