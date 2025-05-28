package com.lib.helper;

import java.sql.*;

public class Connectionprovider {

	
	private static Connection con;
	public static Connection getConnection() {
		
		try {
			
			if(con==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Root");
			Statement st=con.createStatement();
			
			} 
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return con;
	}
	
	
	
}
