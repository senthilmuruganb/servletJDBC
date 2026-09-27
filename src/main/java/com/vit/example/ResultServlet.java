package com.vit.example;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResultServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest request,
	                          HttpServletResponse response)
	            throws ServletException, IOException {
		    HttpSession session = request.getSession(true);
	        response.setContentType("text/html");
	        
	        PrintWriter out = response.getWriter();
	        String searchResult=(String)session.getAttribute("srcResult");
	        out.println("<h1>"+searchResult+"</h1>");
	        //out.println("<p>This Servlet is running using Maven and Tomcat.</p>");
	    }
}
