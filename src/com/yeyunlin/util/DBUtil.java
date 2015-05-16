package com.yeyunlin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/wireless_db?useUnicode=true&characterEncoding=utf-8";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "579315";
	
	public void closeConn(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection openConn(){
		try {
			Class.forName(DB_DRIVER);
			return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
