package com.imcs.bonus.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.imcs.bonus.dao.EmployeeDAOImpl;

public class ConnectionFactory {
	final static Logger log=Logger.getLogger(ConnectionFactory.class);
	private Connection connection;
	private static ConnectionFactory cFactory ;
	private ConnectionFactory(){
		
	}
	private static ConnectionFactory getInstance(){
		if(cFactory==null)
			cFactory=new ConnectionFactory();
		return cFactory;
	}
	public static Connection getConnection(){
		return getInstance().createConnection();
	}
	private Connection createConnection() {
	//	Properties credentialsProps = null;
		//boolean error = true;
	/*	try {
			credentialsProps = new Properties();
			InputStream stream = ClassLoader.getSystemResourceAsStream("credentials.properties");
			if (stream == null) {
				log.error("Error in loading the credentials for JDBC, "
						+ "credentials.properties file with jdbc credentials in the following foramt is required \n"
						+ "userName=userName\npassword=password");
				return null;
			}
			credentialsProps.load(stream);
			error = false;
		} catch (FileNotFoundException e1) {
			log.error("Error in loading the credentials for JDBC, "
					+ "credentials.properties file with jdbc credentials in the following foramt is required \n"
					+ "userName=userName\npassword=password");
		} catch (IOException e1) {
			log.error("Failed to load the file credentials.properties");
		}

		if (error) {
			return null;
		}*/

		try {
			log.info("loading driver");
			//Class.forName(credentialsProps.getProperty("driver.name"));
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}

	/*	String url = credentialsProps.getProperty("url");
		String user = credentialsProps.getProperty("user");
		String password = credentialsProps.getProperty("pwd");
*/
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empbonusdata", "root","root");
			log.info("connection:"+connection);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return connection;
	}
	
}
