package com.example.demo;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class SarojLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		//1.2.3.4 
		//to find the customer ip address
		String uid = req.getParameter("username");
		String pwd = req.getParameter("password");
		System.out.println( req.getQueryString() );
		try {
			if(uid.equals("saroj") && pwd.equals("123")) {
				res.getWriter().write(" login is successful ");
			
			
		
			 // text format response
				
			req.getSession().setAttribute("login", "success");
			res.sendRedirect("welcome.html");
			}
			
			else {
				req.getSession().setAttribute("login", "failed");
				res.getWriter().write("login failed");
			}
			
				byte[] b="login failed".getBytes();
				res.getOutputStream().write(b); // binary format response
				
				req.getSession().setAttribute("login", "failed");
				req.getRequestDispatcher("error.jsp").forward(req, res);
				
			

	} catch(Exception e) {
		//e.printStackTrace();
	}
	}
	
			
	
	
	
	public void doPost() {
		
	}
	
	public void init() {
		System.out.println("System is running for the first time.");
	}
	
	public void destroy() {
		System.out.println("This helps clearing all data.");
	}
}
