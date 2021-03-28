package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection getDBConnection( )  
	{
		Connection conn =null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		// database connection string
		// constants
	    conn = DriverManager.getConnection
	    		("jdbc:mysql://localhost:3306/locust","root","Saritaoj12@"); 
		  return conn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
