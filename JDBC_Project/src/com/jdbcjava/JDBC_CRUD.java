package com.jdbcjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_CRUD {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String databaseName;

			String userName;

			String password;

			Connection con = null;
			
			final String url = "jdbc:mysql://localhost:3306/";
			
//			con = DriverManager.getConnection(url + databaseName, userName, password);
			
			
			
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
		
	}
	
}
