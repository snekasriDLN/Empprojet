package com.emp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.emp.bean.*;
import com.emp.DBUtil.DBUtil;
import com.emp.bean.Employee;
import com.emp.empDAO.EmpInterface;




public class EmployeeDB implements EmpInterface{

	@Override
	public void insertEmployees(List<Employee> empList) {
		
		System.out.println(" Details recieved in implmentation");
		System.out.println(empList.get(0).getEmail());
		
		String sql = "insert into empdetails(name,age,dob,address,salary,desg,email,phone) values(?,?,?,?,?,?,?,?)";     
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		
		try{
		connection  = DBUtil.getConnection();
		connection.setAutoCommit(false);
		 // Employee empList = new Employee();
		for (Employee emp : empList) {
			
			System.out.println(emp.getName());
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, emp.getName());
			
			prepareStatement.setInt(2, emp.getAge());
			prepareStatement.setString(3, emp.getDob());
			prepareStatement.setString(4, emp.getAddress());
			prepareStatement.setInt(5, emp.getSalary());
			prepareStatement.setString(6, emp.getDesg());
			prepareStatement.setString(7, emp.getEmail());
			prepareStatement.setString(8, emp.getPhone());
			
			prepareStatement.executeUpdate();
			System.out.println("values inserted in db");
		}
		
		connection.commit();
		}
		catch(Exception e){
			
			try{
				connection.rollback();
			}
		
			catch (Exception e1) {
				e1.printStackTrace();
			}
				e.printStackTrace();
		}
		
		finally {
			DBUtil.close(connection, prepareStatement, null);
		}
		
}

	@Override
	public boolean isPresent(String userId, int password) {
		
		String sql = "select * from login";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		System.out.println(" uId and password recieved in implmentation ");
		
		List<String> userIdList    = new ArrayList<>();
		List<Integer> passwordList  = new ArrayList<>();
		
		try {
			connection = DBUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
			
				userIdList.add(resultSet.getString("userId"));
				passwordList.add(resultSet.getInt("password"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(connection, prepareStatement, resultSet);
		}
		
		System.out.println(userIdList.get(0)+" "+userIdList.get(1)+" "+userIdList.get(2));
		System.out.println(passwordList.get(0)+" "+passwordList.get(1)+" "+passwordList.get(1));
		
		int count = 0;
		int flag = 0;
		boolean a = false ;
		
		for(int i=0; i<userIdList.size(); i++) {
			
			if(userId.equals(userIdList.get(i)) ){
						if(password == passwordList.get(i)) {
							a = true ;
			}
			
		}
		
		/*if(count == 1 && flag == 1) {
			a = true ;
		}
		else {
			a = false;
		}*/
		
		
		
	}
		return a;
	}
	

	@Override
	public List<Employee> getEmployee(int age) {
		
		System.out.println("getEmployee called ..");
		String sql = "select * from empdetails";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		List<Employee> empList    = new ArrayList<>();
		
		
		try {
			connection = DBUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()){
				Employee emp = new Employee();
				emp.setName(resultSet.getString("Name"));
				emp.setAddress(resultSet.getString("Address"));
				
				empList.add(emp);
				
				System.out.println(emp.getName()+" "+emp.getAddress());
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(connection, prepareStatement, resultSet);
		}
		
		
		return empList;
		
	}

}
