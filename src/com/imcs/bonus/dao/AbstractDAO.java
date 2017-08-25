package com.imcs.bonus.dao;

import java.sql.Connection;

import com.imcs.bonus.util.ConnectionFactory;

public abstract class AbstractDAO {
	public Connection getConnection(){
		
			return ConnectionFactory.getConnection();
		
	}
}
