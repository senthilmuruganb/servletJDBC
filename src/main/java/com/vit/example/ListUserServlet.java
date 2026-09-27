package com.vit.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vit.dao.UserDAO;
import com.vit.model.User;

public class ListUserServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) {
		new UserDAO();
	}
	 @Override
	    protected void doPost(HttpServletRequest request,
	                          HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/html");
	        HttpSession session = request.getSession(true);
	        RequestDispatcher rd;

	        PrintWriter out = response.getWriter();
	        String keyword = request.getParameter("key");
	        ArrayList<User> userList = UserDAO.executeSelect(keyword);
	        session.setAttribute("searchResult", userList);
	        //rd= request.getRequestDispatcher("UserListServlet");
	        //rd.forward(request, response);
	        //response.sendRedirect(request.getContextPath() + "/UserListServlet");
	        response.sendRedirect("UserListServlet");
	        //out.println("<h1>Hello from Servlet!</h1>");
	        //out.println("<p>This Servlet is running using Maven and Tomcat.</p>");
	    }
	 
}