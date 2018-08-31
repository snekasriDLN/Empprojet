package com.emp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.dao.impl.EmployeeDB;

@WebServlet(name="log", urlPatterns="/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" Login doGet() called");
		
		String userId = req.getParameter("userId");
		int password = Integer.parseInt(req.getParameter("password"));
		
		System.out.println("userId and password was passed to DAO ");
		
		PrintWriter out = resp.getWriter();
		out.println(userId+"/<br>"+password);
		
		
		EmployeeDB empDB = new EmployeeDB();
		
		if(empDB.isPresent(userId, password) == true ) {
		
			RequestDispatcher dispatcher = req.getRequestDispatcher("/input.html");
			dispatcher.forward(req, resp); 
			
		}else {
			
			out.println("<center> <h1> <em> UserId and password is incorrect </em> </center> </h1>");
			
		}
		
		
	}

	
	
}
