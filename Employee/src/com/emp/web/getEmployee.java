package com.emp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.bean.Employee;
import com.emp.dao.impl.EmployeeDB;

@WebServlet(name="show", urlPatterns="/age")
public class getEmployee extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		int age = Integer.parseInt(req.getParameter("age"));
		System.out.println(age);
		EmployeeDB empDB = new EmployeeDB();
		List<Employee> empList = empDB.getEmployee(age);
		
		req.setAttribute("emplist", empList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/showEmp.jsp");
		dispatcher.forward(req, resp);

		
	}

	

	
}
