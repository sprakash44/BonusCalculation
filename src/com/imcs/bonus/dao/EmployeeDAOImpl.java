package com.imcs.bonus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.bonus.pojo.Employee;
import com.imcs.bonus.util.ConnectionFactory;

public class EmployeeDAOImpl extends AbstractDAO implements EmployeeDAO {
	final static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	public void loadEmployee(List<Employee> employees) {
		log.info("Loading Employee to DB");
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st = conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("insert into emptable values(?,?,?,?,?,?)")) {
			log.info("batch processing Employee file data");
			conn.setAutoCommit(false);
			st.execute("truncate emptable");
			int count = 1;
			boolean hasLeftOverBatchRecords = true;
			for (Employee bs : employees) {
				ps.setInt(1, bs.getEmpNo());
				ps.setInt(2, bs.getDeptNo());
				ps.setDate(3, new Date(bs.getDoj().getTime()));
				ps.setDate(4, new Date(bs.getDob().getTime()));
				ps.setFloat(5, bs.getSalary());
				ps.setInt(6, bs.getSalGrade());
				ps.addBatch();
				if (count++ % 5 == 0) {
					ps.executeBatch();
					hasLeftOverBatchRecords = false;
				} else {
					hasLeftOverBatchRecords = true;
				}
			}

			if (hasLeftOverBatchRecords) {
				ps.executeBatch();
			}

			conn.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	public List<Employee> getEmployeesByAge(int deptNo) {
		log.info("get employees by age for dept" + deptNo);
		List<Employee> list = new ArrayList<>();
		ResultSet rs = null;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st = conn.createStatement();
				PreparedStatement ps = conn.prepareStatement(
						"select empno,deptno,DOJ,DOB,salary,salgrade from emptable where deptno=? order by DOB")) {
			ps.setInt(1, deptNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Employee(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getFloat(5),
						rs.getInt(6)));
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return list;
	}

	public List<Employee> getEmployeesByJoining(int deptNo) {
		log.info("get employees by Joining date for dept" + deptNo);
		List<Employee> list = new ArrayList<>();
		ResultSet rs = null;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st = conn.createStatement();
				PreparedStatement ps = conn.prepareStatement(
						"select empno,deptno,DOJ,DOB,salary,salgrade from emptable where deptno=? order by DOJ")) {
			ps.setInt(1, deptNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Employee(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getFloat(5),
						rs.getInt(6)));
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return list;
	}

	public Employee getEmployeeByNo(int empNo) {
		ResultSet resultSet = null;
		Employee employee = null;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from emptable where empno=? ")) {
 
			ps.setInt(1, empNo);
			resultSet = ps.executeQuery();
			System.out.println("result set :"+resultSet);
			while(resultSet.next()){
				employee = new Employee(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3),
						resultSet.getDate(4), resultSet.getFloat(5), resultSet.getInt(6));

			}
			
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		System.out.println("Employee in dao... :" + employee);
		return employee;
	}

}
