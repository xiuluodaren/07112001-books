package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String driver = LoadPropertiesFile.getProperty("driverName");
	private static String url = LoadPropertiesFile.getProperty("jdbcUrl");
	private static String username = LoadPropertiesFile.getProperty("jdbcusername");
	private static String password = LoadPropertiesFile.getProperty("jdbcPassword");
	
	public void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection openConnection() {
//		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//		String url = "jdbc:sqlserver://localhost:3342;database=bookbrow1608";
//		String username = "sa";
//		String password = "123";
		try {

			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
