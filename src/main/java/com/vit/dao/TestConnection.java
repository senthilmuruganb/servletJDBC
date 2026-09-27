package com.vit.dao;

import java.sql.Connection;

public class TestConnection {
	private static Connection con=null;
	
	public TestConnection(){
    	try {
    		
			con = DBConnection.getConnection();
			System.out.println("Connection Established...."+con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
