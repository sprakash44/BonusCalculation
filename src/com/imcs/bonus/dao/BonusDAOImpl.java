package com.imcs.bonus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.bonus.pojo.Bonus;
import com.imcs.bonus.util.ConnectionFactory;

public class BonusDAOImpl extends AbstractDAO implements BonusDAO{
	final static Logger logger=Logger.getLogger(BonusDAOImpl.class);
	
	public List<Integer> getDepartments(){
		logger.info("getting all Departments for Bonus");
		List<Integer> list=new ArrayList<>();
		ResultSet rs=null;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("select deptno from bonus")) {
				rs=ps.executeQuery();
				while(rs.next()){
					list.add(rs.getInt(1));
				}
				logger.info("All departments fetched");
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return list;
	}
	public void loadBonus(List<Bonus> bonus) {
		logger.info("Loading bonuses to DB");
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("insert into bonus values(?,?,?)")) {
			logger.info("batch processing file data");
			conn.setAutoCommit(false);
			st.execute("truncate bonus");
			int count = 1;
			boolean hasLeftOverBatchRecords = true;
			for (Bonus bs : bonus) {
				ps.setInt(1, bs.getDeptNo());
				ps.setFloat(2, bs.getAmount());
				ps.setFloat(3, bs.getRemainingAmount());
				ps.addBatch();
				if (count++ % 5 == 0) {
					ps.executeBatch();
					hasLeftOverBatchRecords = false;
				} else {
					hasLeftOverBatchRecords = true;
				}
			}
			
			if(hasLeftOverBatchRecords) {
				ps.executeBatch();
			}
			
			conn.commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
	public List<Bonus> getAllBonus() {
		logger.info("getting all bonus");
		List<Bonus> bonuses=new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("select deptNo,amount,remainingAmount from bonus")) {
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				bonuses.add(new Bonus(rs.getInt(1),rs.getFloat(2),rs.getFloat(3)));
			}
			logger.info("fetced all bonus data");
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bonuses;
	}
	public void updateBonus(int deptNo,float amount) {
		logger.info("updating bonus table");
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("update bonus set remainingAmount=? where deptNo=?")) {
			ps.setFloat(1, amount);
			ps.setInt(2, deptNo);
			ps.executeUpdate();
			logger.info("done updating bonus table for"+deptNo);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}		
	}
}
