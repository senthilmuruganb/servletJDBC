package com.vit.example;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vit.dao.TestConnection;
import com.vit.dao.UserDAO;
public class AuthServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) {
		new UserDAO();
		new TestConnection();
	}
	 @Override
	    protected void doGet(HttpServletRequest request,
	                          HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/html");
	        RequestDispatcher rd;

	        PrintWriter out = response.getWriter();
	        String userId = request.getParameter("userid");
	        String password = request.getParameter("pass");
	        String username = UserDAO.validateLogin(userId, password);
	        if(username==null) {
	        	out.println("<h1>Authentication Failed</h1>");
	        	rd= request.getRequestDispatcher("login.html");
	        	rd.include(request, response);
	        }else {
	        	out.println("<h1>Authentication Success-Welcome"+username+"</h1>");
	        	rd= request.getRequestDispatcher("searchuser.html");
	        	rd.include(request, response);
	        }

	        //out.println("<h1>Hello from Servlet!</h1>");
	        //out.println("<p>This Servlet is running using Maven and Tomcat.</p>");
	    }
	 
}
