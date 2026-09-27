package com.vit.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vit.dao.UserDAO;
import com.vit.model.User;

public class UserListServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest request,
	                          HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        
	        HttpSession session = request.getSession(true);
	        ArrayList<User> userList = (ArrayList<User>)
	        		session.getAttribute("searchResult");
	        if(userList==null) {
	        	out.println("<h1>No User Data Found</h1>");
	        }
	        else {
	        	Iterator it = userList.iterator();
	        	out.println("<table border='1'>");
	        	out.println("<tr>");
	        	out.println("<th>Username</th>");
	        	out.println("<th>Email</th>");
	        	out.println("<th>Age</th>");
	        	out.println("<th>UserId</th>");
	        	out.println("</tr>");
	        	while(it.hasNext()) {
	        		User user = (User)it.next();
	        		out.println("<tr>");
	        		out.println("<td>"+user.getUsername());
	        		out.println("<td>"+user.getEmail());
	        		out.println("<td>"+user.getAge());
	        		out.println("<td>"+user.getUserId());
	        		out.println("</tr>");
	        	}
	        	out.println("</table>");
	        	
	        }
	        
	    }
}
