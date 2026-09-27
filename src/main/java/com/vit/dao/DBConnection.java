package com.vit.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	 private static Connection connection;
	 private static final String  URL="jdbc:postgresql://localhost:5432/academics";
	 private static final String USER = "postgres";
	 private static final String PASSWORD = "admin";
	 private DBConnection() throws Exception {
		Class.forName("org.postgresql.Driver");
	    System.out.println("PostgreSQL driver loaded");
	    connection = DriverManager.getConnection(URL,USER,PASSWORD);
	 }
	  public static Connection getConnection() throws Exception {
		  if (connection == null || connection.isClosed()) 
			  new DBConnection();
	      return connection;
	   }
}
