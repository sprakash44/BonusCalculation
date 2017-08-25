package com.imcs.bonus.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.bonus.dao.BonusDAO;
import com.imcs.bonus.dao.BonusDAOImpl;
import com.imcs.bonus.pojo.Bonus;

public class BonusServiceImpl implements BonusService{
	private BonusDAO bimpl=new BonusDAOImpl();
	
	public void loadBonus(List<Bonus> bonus){
		bimpl.loadBonus(bonus);
	}
	public List<Bonus> getBonus() {
		return bimpl.getAllBonus();
	}
	public void updateBonus(int	deptNo, float rem){
		bimpl.updateBonus(deptNo,rem);
	}
}
