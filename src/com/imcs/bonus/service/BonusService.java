package com.imcs.bonus.service;

import java.util.List;

import com.imcs.bonus.pojo.Bonus;

public interface BonusService {	
	public List<Bonus> getBonus();
	public void loadBonus(List<Bonus> bonus);
	public void updateBonus(int	deptNo, float rem);
}
