package com.emp.empDAO;

import java.util.List;
import com.emp.bean.Employee;

public interface EmpInterface {

	public void insertEmployees(List<Employee> list);
	
	public boolean isPresent(String userId , int password);
	
	public List<Employee> getEmployee(int age);
	
}
