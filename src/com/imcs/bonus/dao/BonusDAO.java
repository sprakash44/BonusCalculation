package com.imcs.bonus.dao;

import java.util.List;

import com.imcs.bonus.pojo.Bonus;

public interface BonusDAO {
	public List<Integer> getDepartments();
	public void loadBonus(List<Bonus> bonus);
	public List<Bonus> getAllBonus();
	public void updateBonus(int deptNo,float amount);
}
