package com.imcs.bonus.pojo;

import org.apache.log4j.Logger;

public class Bonus {
	final static Logger logger=Logger.getLogger(Bonus.class);
	private int deptNo;
	private float amount;
	private float remainingAmount;
	public Bonus(int deptNo, float amount,float remAmount) {
		this.deptNo=deptNo;
		this.amount=amount;
		remainingAmount=remAmount;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(float remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
}
