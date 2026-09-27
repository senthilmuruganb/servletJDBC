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

public class SearchServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest request,
	                          HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/html");
	        HttpSession session = request.getSession(true);
	        String result=null;	
	        PrintWriter out = response.getWriter();
	        String keyword=request.getParameter("key");
	        String source[]= {"Hello","from","world","systems"};
	        for(String s:source) {
	        	if(s.equals(keyword)) {
	        		result="Search Success";
	        		
	        	}
	        }
	        if(result==null) {
	        	result="Search Failed";
	        }
	        session.setAttribute("srcResult", result);
	        RequestDispatcher rd = request.getRequestDispatcher("ResultServlet");
	        rd.forward(request, response);
	        out.println("<h1>Hello from Servlet!</h1>");
	        out.println("<p>This Servlet is running using Maven and Tomcat.</p>");
	    }


}
