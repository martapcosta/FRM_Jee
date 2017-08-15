package com.marta.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {

	//FIXME change dbname, username and password
	private static String dbUrl = "jdbc:mysql://localhost:3306/issues_tracking_database?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String dbUsername = "demo";
	private static String dbPassword = "demo";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					dbUrl, dbUsername, dbPassword);
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
			//Nothing to do
		}
	}
}
