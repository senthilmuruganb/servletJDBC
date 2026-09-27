package com.vit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.vit.model.User;

public class UserDAO {
	
	private static Connection con=null;
	
    
    public UserDAO(){
    	try {
    		
			con = DBConnection.getConnection();
			System.out.println("Connection Established.UserDAO..."+con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String validateLogin(String userId,String password) {
    	String username=null;
    	try {
    		String sql="SELECT username FROM public.userdata where userid=? and password=?";
    		PreparedStatement pstmt= con.prepareStatement(sql);
    		pstmt.setString(1, userId);
    		pstmt.setString(2, password);
    		ResultSet rs = pstmt.executeQuery();
    		if(rs.next()) {
    			username= rs.getString("username");
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("Exception in validateLogin"+e);
    	}
    	return username;
    	
    }
    
    public static ArrayList<User> executeSelect(String pattern){
    	ArrayList<User> userList=new ArrayList<User>();
    	try {
    		String sql="SELECT * FROM public.userdata where username like ?";
    		PreparedStatement pstmt= con.prepareStatement(sql);
    		pstmt.setString(1, pattern+"%");
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {
    			User user = new User(rs.getString(1),rs.getString(2),
    					rs.getString(3),rs.getInt(4),rs.getString(5));
    			userList.add(user);
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("Exception in executeSelect"+e);
    	}
    	return userList;
    }

}
