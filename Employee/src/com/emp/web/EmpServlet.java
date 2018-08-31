package com.emp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.bean.Employee;
import com.emp.dao.impl.EmployeeDB;
import com.emp.empDAO.EmpInterface;

@WebServlet(name="serv" , urlPatterns="/input")
public class EmpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
						throws ServletException, IOException {
		
		System.out.println("doGet() called");
		
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String dob = req.getParameter("dob");
		String address = req.getParameter("address");
		int salary = Integer.parseInt(req.getParameter("salary"));
		String desg = req.getParameter("desg");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
		PrintWriter out = resp.getWriter();
		out.println(name+"<br>"+age+"<br>"+dob+"<br>"+address+"<br>"+salary+"<br>"+
							desg+"<br>"+email+"<br>"+phone);
		
		List<Employee> empList = new ArrayList<>();
		
		Employee emp = new Employee();
		emp.setName(name);
		emp.setAge(age);
		emp.setDob(dob);
		emp.setAddress(address);
		emp.setSalary(salary);
		emp.setDesg(desg);
		emp.setEmail(email);
		emp.setPhone(phone);
		
		empList.add(emp);
		
		EmployeeDB empDB = new EmployeeDB();
		empDB.insertEmployees(empList);
		
		System.out.print("passed to dao implementation");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/enterAge.html");
		dispatcher.forward(req, resp); 
		
	}
	
	
}
