package com.imcs.bonus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.imcs.bonus.pojo.EmployeeBonus;
import com.imcs.bonus.pojo.Employee;
import com.imcs.bonus.util.ConnectionFactory;

public class EmployeeBonusDAOImpl extends AbstractDAO implements EmployeeBonusDAO{
	final static Logger log=Logger.getLogger(EmployeeBonusDAOImpl.class);
	
	public void setEmpBonus(EmployeeBonus empBonus) {
		log.info("setting emp bonus"+empBonus.getEmpNo());
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("insert into employeebonus values(?,?,?,?)")) {
			ps.setInt(1, empBonus.getEmpNo());
			ps.setString(2, empBonus.getStatus());
			ps.setFloat(3, empBonus.getAmount());
			ps.setDate(4, new Date(empBonus.getDateAllocated().getTime()));
			ps.executeUpdate();
				
		}catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}
	
	
}
