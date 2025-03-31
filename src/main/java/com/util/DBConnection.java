package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url="jdbc:mysql://localhost:3306/event_management?user=root&password=12345";
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Mysql JDBC Driver not found");
		}
	}

}
