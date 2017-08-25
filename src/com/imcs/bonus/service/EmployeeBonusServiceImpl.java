package com.imcs.bonus.service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

import com.imcs.bonus.dao.EmployeeBonusDAO;
import com.imcs.bonus.dao.EmployeeBonusDAOImpl;
import com.imcs.bonus.pojo.Bonus;
import com.imcs.bonus.pojo.EmployeeBonus;
import com.imcs.bonus.pojo.Employee;

public class EmployeeBonusServiceImpl implements EmployeeBonusService{	
	
	BonusService bonusService=new BonusServiceImpl();
	EmployeeService empService=new EmployeeServiceImpl();
	private EmployeeBonusDAO empBDao=new EmployeeBonusDAOImpl();
	final static Logger log=Logger.getLogger(EmployeeBonusService.class);
	public void allocateBonus() {
		log.info("STARTED ALLOCATION PROCESS");
		try{
			List<Bonus> list=bonusService.getBonus();
			ExecutorService executorService = Executors.newFixedThreadPool(4);
			for(Bonus b:list){
				log.info("STARTED ALLOCATING BONUS FOR DEPT : "+b.getDeptNo());
				List<Employee> emps=empService.getEmployees(b.getDeptNo());
				Thread thread=new Thread(){
					public void run(){
						float rAmount=b.getRemainingAmount();
						for(Employee emp:emps){
							rAmount=assignBonus(emp,rAmount);
						}
						bonusService.updateBonus(b.getDeptNo(),rAmount);
					}
				};
				thread.setName(""+b.getDeptNo());
				thread.join();
				executorService.execute(thread);
				log.info("DONE ALLOCATION FOR DEPTNO :"+b.getDeptNo());
			}
			executorService.shutdown();
		}catch (Exception e) {
			log.error("Exception : "+e.getMessage());
		}
	}
	public float assignBonus(Employee emp, float balanceAmount) {
		log.info("alloting bonus for"+emp.getEmpNo());
		float empBonus=0.0f;
		switch(emp.getSalGrade()){
		case 1:
			empBonus=emp.getSalary()*0.1f;
			break;
		case 2:
			empBonus=emp.getSalary()*0.15f;
			break;
		case 3:
			empBonus=emp.getSalary()*0.2f;
			break;
		case 4:
			empBonus=emp.getSalary()*0.25f;
			break;
		default:
			empBonus=emp.getSalary()*0.05f;
			break;	
		}
		if(balanceAmount==0.0f){
			empBDao.setEmpBonus(new EmployeeBonus(emp.getEmpNo(),"INC",0.0f,new Date()));
			log.info("Bonus for Employee"+emp.getEmpNo()+" is $"+0.0f);
			return balanceAmount;
		}
		else if(balanceAmount>empBonus){
			empBDao.setEmpBonus(new EmployeeBonus(emp.getEmpNo(),"CMP",empBonus,new Date()));
			log.info("Bonus for Employee"+emp.getEmpNo()+" is $"+empBonus);
			return balanceAmount-empBonus;
		}
		else {
			empBDao.setEmpBonus(new EmployeeBonus(emp.getEmpNo(),"PAR",balanceAmount,new Date()));
			log.info("Bonus for Employee"+emp.getEmpNo()+" is $"+balanceAmount);
			return 0.0f;
		}	
	}
}
